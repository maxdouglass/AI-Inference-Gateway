package com.maxdouglass.inferencegateway.core;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class Batcher {
    private static final Logger log = LoggerFactory.getLogger(Batcher.class);

    private final RequestQueue queue;
    private final WorkerPool workerPool;

    private final int maxBatchSize = 8;
    private final long flushIntervalMs = 50;

    private volatile boolean running = true;
    private Thread workerThread;

    public Batcher(RequestQueue queue, WorkerPool workerPool) {
        this.queue = queue;
        this.workerPool = workerPool;
    }

    @PostConstruct
    public void start() {
        workerThread = new Thread(this::runLoop, "batcher-thread");
        workerThread.setDaemon(true);
        workerThread.start();
        log.info("Batcher started with a max batch size of  {}, and a flush interval in Milliseconds of {}", maxBatchSize, flushIntervalMs);
    }

    @PreDestroy
    public void stop() throws InterruptedException {
        running = false;
        if (workerThread != null) {
            workerThread.interrupt();
            workerThread.join(1000);
        }
        log.info("Batcher stopped");
    }

    private void runLoop() {
        try {
            while (running) {
                RequestObject first = queue.take();
                List<RequestObject> batch = new ArrayList<>();
                batch.add(first);

                long flushMarker = System.currentTimeMillis() + flushIntervalMs;

                while (batch.size() < maxBatchSize) {
                    long remainingTime = flushMarker - System.currentTimeMillis();
                    if (remainingTime <= 0) break;

                    RequestObject nextRequest = queue.poll(remainingTime);
                    if (nextRequest == null) break;

                    batch.add(nextRequest);
                }

                log.info("Flushing batch at {} with size {}", Instant.now(), batch.size());
                workerPool.submitBatch(batch);
            }
        } catch (InterruptedException e) {
            if (running) {
                log.warn("Batcher interrupted unexpectedly", e);
            } else{
                log.info("Batcher shutting down");
            }
        }
    }
}
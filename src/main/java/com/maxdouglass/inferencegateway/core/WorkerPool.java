package com.maxdouglass.inferencegateway.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerPool {
    private static final Logger log = LoggerFactory.getLogger(WorkerPool.class);
    private final ExecutorService executorService;

    public WorkerPool() {
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void submitBatch(List<RequestObject> batch) {
        executorService.submit(() -> {
            log.info("Procession batch of size: {} ", batch.size());
            for (RequestObject request: batch){
                log.info(" - requestId={} prompt={}", request.getRequestId(), request.getRequest().prompt());
            }
        });
    }
}
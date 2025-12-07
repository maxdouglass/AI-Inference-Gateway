package com.maxdouglass.inferencegateway.core;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class RequestQueue{
    private final BlockingQueue<RequestObject> queue = new LinkedBlockingQueue<>();

    public void submit(RequestObject request) throws InterruptedException {
        queue.put(request);
    }

    public RequestObject take() throws InterruptedException {
        return queue.take();
    }

    public RequestObject poll(long timeoutMillis) throws InterruptedException {
        return queue.poll(timeoutMillis, TimeUnit.MILLISECONDS);
    }
}
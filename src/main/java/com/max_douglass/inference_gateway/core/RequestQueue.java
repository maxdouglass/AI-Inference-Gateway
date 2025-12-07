package com.max_douglass.inference_gateway.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue{
    private final BlockingQueue<RequestObject> queue = new LinkedBlockingQueue<>();

    public void submit(RequestObject request) throws InterruptedException {
        queue.put(request);
    }

    public RequestObject take() throws InterruptedException {
        return queue.take();
    }
}
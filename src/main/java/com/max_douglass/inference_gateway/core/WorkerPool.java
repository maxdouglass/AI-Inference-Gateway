package com.max_douglass.inference_gateway.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPool {
    private final ExecutorService executorService;

    public WorkerPool(int threads){
        this.executorService = Executors.newFixedThreadPool(threads);
    }
}
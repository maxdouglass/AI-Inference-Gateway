package com.maxdouglass.inferencegateway.core;

import com.maxdouglass.inferencegateway.api.InferenceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
public class RequestObject {
    private final String requestId;
    private final InferenceRequest request;
    private final CompletableFuture<Object> future;
}
package com.max_douglass.inference_gateway.core;

import com.max_douglass.inference_gateway.api.InferenceRequest;
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
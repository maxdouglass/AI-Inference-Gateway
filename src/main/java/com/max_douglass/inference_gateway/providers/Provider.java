package com.max_douglass.inference_gateway.providers;

import com.max_douglass.inference_gateway.api.InferenceRequest;

import java.util.List;

public interface Provider {
    Object inferBatch(List<InferenceRequest> requests);
}
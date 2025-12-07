package com.maxdouglass.inferencegateway.providers;

import com.maxdouglass.inferencegateway.api.InferenceRequest;

import java.util.List;

public interface Provider {
    Object inferBatch(List<InferenceRequest> requests);
}
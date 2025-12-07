package com.maxdouglass.inferencegateway.providers;

import com.maxdouglass.inferencegateway.api.InferenceRequest;

import java.util.List;

public class OpenAiProvider implements Provider {

    @Override
    public Object inferBatch(List<InferenceRequest> requests){
        return null;
    }
}
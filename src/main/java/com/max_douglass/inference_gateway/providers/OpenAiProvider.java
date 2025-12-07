package com.max_douglass.inference_gateway.providers;

import com.max_douglass.inference_gateway.api.InferenceRequest;
import com.max_douglass.inference_gateway.providers.Provider;

import java.util.List;

public class OpenAiProvider implements Provider {

    @Override
    public Object inferBatch(List<InferenceRequest> requests){
        return null;
    }
}
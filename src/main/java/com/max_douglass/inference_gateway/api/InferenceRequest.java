package com.max_douglass.inference_gateway.api;

import java.util.Map;

public record InferenceRequest(String prompt, String model, Map<String, Object> params){ }



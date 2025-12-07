package com.maxdouglass.inferencegateway.api;

import java.util.Map;

public record InferenceRequest(String prompt, String model, Map<String, Object> params){ }



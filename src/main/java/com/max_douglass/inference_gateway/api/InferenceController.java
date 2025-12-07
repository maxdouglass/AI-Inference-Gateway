package com.max_douglass.inference_gateway.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class InferenceController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @PostMapping("/infer")
    public Map<String, Object> infer(@RequestBody InferenceRequest inferenceRequest) {
        String id = UUID.randomUUID().toString();
        return Map.of("status", "queued", "requestId", id);
    }
}
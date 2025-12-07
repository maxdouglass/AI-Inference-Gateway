package com.maxdouglass.inferencegateway.api;

import com.maxdouglass.inferencegateway.core.RequestObject;
import com.maxdouglass.inferencegateway.core.RequestQueue;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class InferenceController {
    private static final Logger log = LoggerFactory.getLogger(InferenceController.class);

    private final RequestQueue requestQueue;

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @PostMapping("/infer")
    public Map<String, Object> infer(@RequestBody InferenceRequest inferenceRequest) throws InterruptedException {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> future = new CompletableFuture<>();
        RequestObject requestObject = new RequestObject(id, inferenceRequest, future);

        requestQueue.submit(requestObject);
        log.info("Enqueued requestId={} prompt={} ", id, requestObject.getRequest().prompt());

        return Map.of("status", "queued", "requestId", id);
    }
}
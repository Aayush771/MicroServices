package com.microservice.currency_exchange_service.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    // @Retry(name="sample-api", fallbackMethod = "HardCodedResponse")
    @CircuitBreaker(name="sample-api", fallbackMethod = "HardCodedResponse")
    public String getMethodName() {
        logger.info("Sample api call Recived");
       ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/currency-exchange/from/USD/to/INR", String.class);

       return response.getBody();
    }
    public String HardCodedResponse(Exception exception) {
        return "FallBack response";
    }
}

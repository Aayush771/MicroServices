package com.microservices.limits_services.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limits_services.Config.Configuration;
import com.microservices.limits_services.Entity.Limits;

@RestController
public class LimitController {
    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limits retriveLimits() {
        //return new Limits(1,1000);
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}

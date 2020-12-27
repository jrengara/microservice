package com.example.limitservice;

import com.example.limitservice.bean.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class LimitConfigurationController {

    @Autowired
    private Configuration configuration;


    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration(){
        return new LimitConfiguration(configuration.getMax(),configuration.getMin());
    }

}

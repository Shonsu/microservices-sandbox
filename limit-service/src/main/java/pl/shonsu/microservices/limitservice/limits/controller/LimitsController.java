package pl.shonsu.microservices.limitservice.limits.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.microservices.limitservice.limits.configuration.Configuration;

@RestController
public class LimitsController {
    private final Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}

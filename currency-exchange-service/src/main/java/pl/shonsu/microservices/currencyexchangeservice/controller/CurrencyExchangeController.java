package pl.shonsu.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.microservices.currencyexchangeservice.controller.dto.CurrencyExchange;
import pl.shonsu.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
    private final static Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} {}", from, to);
        CurrencyExchange exchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unable find data for %s to %s".formatted(from, to)));
        exchange.setEnvironment(environment.getProperty("local.server.port"));
        return exchange;
    }
}

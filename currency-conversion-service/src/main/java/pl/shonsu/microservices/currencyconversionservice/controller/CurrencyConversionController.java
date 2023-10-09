package pl.shonsu.microservices.currencyconversionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pl.shonsu.microservices.currencyconversionservice.controller.dto.CurrencyConversion;
import pl.shonsu.microservices.currencyconversionservice.feign.CurrencyExchangeProxy;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    private final static Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    private final WebClient webClient;

    private final CurrencyExchangeProxy proxy;

    public CurrencyConversionController(WebClient webClient, CurrencyExchangeProxy proxy) {
        this.webClient = webClient;
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        logger.info("calculateCurrencyConversion called with {} {}", from, to);
        CurrencyConversion currencyConversion = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/currency-exchange/from/{from}/to/{to}")
                .build(from, to)).retrieve().bodyToMono(CurrencyConversion.class).block();
        return new CurrencyConversion(currencyConversion.id(), from, to, quantity,
                currencyConversion.conversionMultiple(),
                quantity.multiply(currencyConversion.conversionMultiple()),
                currencyConversion.environment() + " " + "WebClient");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity) {
        logger.info("calculateCurrencyConversionFeign called with {} {}", from, to);
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.id(), from, to, quantity,
                currencyConversion.conversionMultiple(),
                quantity.multiply(currencyConversion.conversionMultiple()),
                currencyConversion.environment() + " " + "Feign");
    }
}

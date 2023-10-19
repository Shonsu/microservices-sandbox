package pl.shonsu.microservices.currencyconversionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.shonsu.microservices.currencyconversionservice.controller.dto.CurrencyConversion;

@FeignClient(name="currency-exchange", url = "localhost:8000")
//@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}

package pl.shonsu.microservices.currencyconversionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.shonsu.microservices.currencyconversionservice.controller.dto.CurrencyConversion;

//@FeignClient(name="currency-exchange", url = "localhost:8000")
//@FeignClient(name = "currency-exchange")
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}

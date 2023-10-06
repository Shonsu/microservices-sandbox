package pl.shonsu.microservices.currencyconversionservice.controller.dto;

import java.math.BigDecimal;

public record CurrencyConversion(
        Long id,
        String from,
        String to,
        BigDecimal quantity,
        BigDecimal conversionMultiple,
        BigDecimal totalCalculatedAmount,
        String environment
) {
}

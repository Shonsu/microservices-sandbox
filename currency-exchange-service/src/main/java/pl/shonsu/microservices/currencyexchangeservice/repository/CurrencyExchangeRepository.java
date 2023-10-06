package pl.shonsu.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shonsu.microservices.currencyexchangeservice.controller.dto.CurrencyExchange;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}

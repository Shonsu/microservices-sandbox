package pl.shonsu.microservices.currencyconversionservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Value("${app.exchangeServiceName}:8000")
    private String exchangeServiceHostname;
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(exchangeServiceHostname).build();
    }

//    @Bean
//    public MicrometerCapability micrometerCapability(MeterRegistry meterRegistry) {
//        return new MicrometerCapability(meterRegistry);
//    }

//    @Bean
//    public MicrometerObservationCapability micrometerObservationCapability(ObservationRegistry registry) {
//        return new MicrometerObservationCapability(registry);
//    }
}

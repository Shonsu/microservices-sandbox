package pl.shonsu.microservices.currencyconversionservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8000").build();
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

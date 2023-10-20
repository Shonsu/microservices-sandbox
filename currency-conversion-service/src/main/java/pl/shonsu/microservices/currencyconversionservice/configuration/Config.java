package pl.shonsu.microservices.currencyconversionservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Value("${app.exchangeServiceName}")
    private String exchangeServiceHostname;
    @Value("${app.exchangeServicePort}")
    private String exchangeServicePort;

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(exchangeServiceHostname + ":" + exchangeServicePort).build();
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

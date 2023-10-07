package pl.shonsu.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(spec -> spec.path("/get")
                        .filters(f->f.addRequestHeader("X-Provided-By", "Shonsu inc"))
                        .uri("http://httpbin.org:80"))
                .route(spec -> spec.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(spec -> spec.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(spec -> spec.path("/currency-conversion-new/**")
                        .filters(f->f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"
                        ))
                        .uri("lb://currency-conversion"))
                .route(spec -> spec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .build();
    }
}

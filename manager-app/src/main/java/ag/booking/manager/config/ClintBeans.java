package ag.booking.manager.config;

import ag.booking.manager.client.RestClientProductRestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClintBeans {
    @Bean
    public RestClientProductRestClient productRestClient(
            @Value("${booking.service.catalogue.uri:http://localhost:8081}") String catalogueBaseUri) {
        return new RestClientProductRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());

    }
}

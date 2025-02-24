package ag.booking.manager.config;

import ag.booking.manager.client.ProductsRestClient;
import ag.booking.manager.client.RestClientProductRestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {
    @Bean
    public RestClientProductRestClient productRestClient(
            @Value("${booking.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${booking.services.catalogue.username:}") String catalogueUsername,
            @Value("${booking.services.catalogue.password:}") String cataloguePassword) {
        System.out.println("Используемый логин: " + catalogueUsername);
        System.out.println("Используемый пароль: " + cataloguePassword);

        return new RestClientProductRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }
}
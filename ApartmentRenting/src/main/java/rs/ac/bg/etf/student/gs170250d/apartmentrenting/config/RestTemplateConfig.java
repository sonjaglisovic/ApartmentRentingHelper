package rs.ac.bg.etf.student.gs170250d.apartmentrenting.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(10_000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(10_000);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        restTemplateBuilder = restTemplateBuilder.uriTemplateHandler(factory);
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        return restTemplateBuilder.build();
    }
}

package com.flipkartservice.flipkartapigateway.authorization;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.AuthConfig> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthValidator authValidator;

    public AuthFilter() {
        super(AuthConfig.class);
    }

    @Override
    public GatewayFilter apply(AuthConfig config) {
        return ((exchange,chain) -> {
            if(authValidator.isSecuredRequest.test(exchange.getRequest())) {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Invalid Access");
                }

                String AUTH_BEARER_TOKEN = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if(AUTH_BEARER_TOKEN != null && AUTH_BEARER_TOKEN.startsWith("Bearer")) {
                    String AUTH_USER_TOKEN = AUTH_BEARER_TOKEN.substring(7);
                    System.out.println(AUTH_USER_TOKEN);
                    try{
                        restTemplate.getForObject("http://localhost:5001//authorize/validateToken?token=" + AUTH_USER_TOKEN, String.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Unauthorized request");
                    }
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class AuthConfig {

    }
}

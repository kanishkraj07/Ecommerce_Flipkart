package com.serviceregistry.flipkartserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlipkartServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlipkartServiceRegistryApplication.class, args);
    }

}

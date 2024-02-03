package com.flipkartserver.flipkartconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FlipkartConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartConfigServerApplication.class, args);
	}

}

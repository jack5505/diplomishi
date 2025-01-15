package com.tuit.diplomish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class DiplomishApplication {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(DiplomishApplication.class, args);
    }

}

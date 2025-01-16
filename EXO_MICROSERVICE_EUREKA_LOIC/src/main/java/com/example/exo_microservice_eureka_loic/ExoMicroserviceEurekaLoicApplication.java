package com.example.exo_microservice_eureka_loic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExoMicroserviceEurekaLoicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExoMicroserviceEurekaLoicApplication.class, args);
    }

}

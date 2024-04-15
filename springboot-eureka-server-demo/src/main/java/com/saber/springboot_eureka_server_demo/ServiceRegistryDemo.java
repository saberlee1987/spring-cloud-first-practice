package com.saber.springboot_eureka_server_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryDemo {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryDemo.class,args);
    }
}

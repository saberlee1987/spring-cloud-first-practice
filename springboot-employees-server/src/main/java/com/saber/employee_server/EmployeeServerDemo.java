package com.saber.employee_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.saber")
@EnableDiscoveryClient
public class EmployeeServerDemo {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeServerDemo.class, args);
    }
}

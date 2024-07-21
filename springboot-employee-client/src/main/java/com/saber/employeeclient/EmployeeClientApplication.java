package com.saber.employeeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.saber")
@EnableDiscoveryClient
public class EmployeeClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeClientApplication.class,args);
    }
}

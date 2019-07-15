package com.fy.travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
@EnableDiscoveryClient
public class TravelsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelsServerApplication.class, args);
    }

}

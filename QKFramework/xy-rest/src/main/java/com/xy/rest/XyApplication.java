package com.xy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xy"})
public class XyApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyApplication.class, args);
    }
}

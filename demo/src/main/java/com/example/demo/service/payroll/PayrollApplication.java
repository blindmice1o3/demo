package com.example.demo.service.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// "you need to store employee objects in an H2 in-memory database, and access them via JPA"

// "@SpringBootApplication is a meta-annotation that pulls in [component scanning],
// [autoconfiguration], and [property support]. In essence, it will fire up a servlet container
// and serve up our service."

// "an application with no data isn’t very interesting, so let’s preload it. The follow[ing]
// class [LoadDatabase] will get loaded automatically by Spring."

@SpringBootApplication
public class PayrollApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

}

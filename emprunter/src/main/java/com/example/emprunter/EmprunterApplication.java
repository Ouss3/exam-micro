package com.example.emprunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

public class EmprunterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmprunterApplication.class, args);
    }

}

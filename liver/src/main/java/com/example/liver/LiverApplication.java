package com.example.liver;

import com.example.liver.entites.Disciplines;
import com.example.liver.entites.Liver;
import com.example.liver.repository.LiverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiverApplication.class, args);
    }
   @Bean
    CommandLineRunner start(LiverRepository liverRepository){
        return args -> {
            liverRepository.save(Liver.builder().nom("oussama").disciplines(Disciplines.CHIMIE).build());

        };}
}

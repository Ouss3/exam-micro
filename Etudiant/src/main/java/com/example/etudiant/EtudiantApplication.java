package com.example.etudiant;

import com.example.etudiant.entites.Etudiant;
import com.example.etudiant.repository.EtudientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EtudientRepository clientRepository ){
        return args -> {

                Etudiant c = Etudiant.builder().email("user1@um5.ac.ma").password("MTIzNA==").token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyMUBnYW1pbC5jb20iLCJpYXQiOjE1MTYyMzkwMjJ9.1jDbMhO2Wfp8ihAzg4PJzKHZfB_3bXA0jJZ5lksqxks").haveform(false).formcode(null).build();
                clientRepository.save(c);
            Etudiant d = Etudiant.builder().email("user2@um5.ac.ma").password("MTIzNDU=").token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyMkBnYW1pbC5jb20iLCJpYXQiOjE1MTYyMzkwMjJ9.5nP4kJj1ds1mtAadqQvfpwjSHIVMMTn-_AqRZyNrggE").haveform(false).formcode(null).build();
            clientRepository.save(d);

        };}

}

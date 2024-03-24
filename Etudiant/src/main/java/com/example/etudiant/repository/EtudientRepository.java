package com.example.etudiant.repository;


import com.example.etudiant.entites.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudientRepository extends JpaRepository<Etudiant, String> {
}

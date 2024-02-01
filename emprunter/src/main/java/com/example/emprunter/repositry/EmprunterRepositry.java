package com.example.emprunter.repositry;

import com.example.emprunter.entites.Emprunter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprunterRepositry extends JpaRepository<Emprunter, Integer> {
}

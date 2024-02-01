package com.example.liver.repository;


import com.example.liver.entites.Liver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiverRepository extends JpaRepository<Liver,Integer> {
}

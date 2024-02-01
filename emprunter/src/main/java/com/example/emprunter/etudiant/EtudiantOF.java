package com.example.emprunter.etudiant;

import com.example.emprunter.model.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient("ETUDIENT-SEVICE")
public interface EtudiantOF {
    @GetMapping("/api/etudiants")
    List<Etudiant> getAllEtudiants();
    @GetMapping("/api/etudiant/{id}")
    Etudiant getEtudiantById(@PathVariable Integer id);
}

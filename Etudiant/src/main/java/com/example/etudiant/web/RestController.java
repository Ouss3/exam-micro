package com.example.etudiant.web;


import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;
import com.example.etudiant.entites.Etudiant;
import com.example.etudiant.repository.EtudientRepository;
import com.example.etudiant.services.ServicesInterface;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {


    EtudientRepository etudientRepository;



    public RestController( EtudientRepository etudientRepository) {

        this.etudientRepository = etudientRepository;
    }

    @GetMapping("/etudiant/{id}")
    public Etudiant getEtudiantemail(@PathVariable String id){
        return etudientRepository.findById(id).orElseThrow();
    }


//    @GetMapping("/etudiants")
//    public List<ReponseDTO> getAllEtudiants(){
//        return servicesInterface.getAllEtudiants();
//    }
//
//
//    @PostMapping("/etudiant")
//    public void addEtudiant(@RequestBody RequestDTO requestDTO){
//        servicesInterface.addEtudiant(requestDTO);
//    }
//
//    @PutMapping("/etudiant/{id}")
//    public void updateEtudiant(@PathVariable Integer id, @RequestBody RequestDTO requestDTO){
//        servicesInterface.updateEtudiant(id,requestDTO);
//    }
//
//    @DeleteMapping("/etudiant/{id}")
//    public void deleteEtudiant(@PathVariable Integer id){
//        servicesInterface.deleteEtudiant(id);
//    }
}

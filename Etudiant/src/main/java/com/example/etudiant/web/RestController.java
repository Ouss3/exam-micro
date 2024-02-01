package com.example.etudiant.web;


import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;
import com.example.etudiant.services.ServicesInterface;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    ServicesInterface servicesInterface;

    public RestController(ServicesInterface servicesInterface) {
        this.servicesInterface = servicesInterface;
    }

    @GetMapping("/etudiants")
    public List<ReponseDTO> getAllEtudiants(){
        return servicesInterface.getAllEtudiants();
    }

    @GetMapping("/etudiant/{id}")
    public ReponseDTO getEtudiantById(@PathVariable Integer id){
        return servicesInterface.getEtudiantById(id);
    }

    @PostMapping("/etudiant")
    public void addEtudiant(@RequestBody RequestDTO requestDTO){
        servicesInterface.addEtudiant(requestDTO);
    }

    @PutMapping("/etudiant/{id}")
    public void updateEtudiant(@PathVariable Integer id, @RequestBody RequestDTO requestDTO){
        servicesInterface.updateEtudiant(id,requestDTO);
    }

    @DeleteMapping("/etudiant/{id}")
    public void deleteEtudiant(@PathVariable Integer id){
        servicesInterface.deleteEtudiant(id);
    }
}

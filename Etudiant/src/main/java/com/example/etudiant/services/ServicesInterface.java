package com.example.etudiant.services;


import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;

import java.util.List;

public interface ServicesInterface {
    List<ReponseDTO> getAllEtudiants();
    ReponseDTO getEtudiantById(Integer id);
    void addEtudiant(RequestDTO requestDTO);
    void updateEtudiant(Integer id, RequestDTO requestDTO);
    void deleteEtudiant(Integer id);
}

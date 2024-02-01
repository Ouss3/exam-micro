package com.example.etudiant.mapper;

import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;
import com.example.etudiant.entites.Etudiant;

public interface MapperInterface {

    Etudiant RequestToEtudiant(RequestDTO requestDTO);
    ReponseDTO EtudiantToReponseDTO(Etudiant etudiant);
}

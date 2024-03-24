package com.example.etudiant.mapper;


import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;
import com.example.etudiant.entites.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Mapper implements MapperInterface {
    @Override
    public Etudiant RequestToEtudiant(RequestDTO requestDTO) {
        Etudiant etudiant = new Etudiant();

        etudiant.setEmail(requestDTO.getEmail());
        etudiant.setToken(requestDTO.getToken());
        etudiant.setPassword(requestDTO.getPassword());
        return etudiant;
    }

    @Override
    public ReponseDTO EtudiantToReponseDTO(Etudiant etudiant) {
        ReponseDTO reponseDTO = new ReponseDTO();
        BeanUtils.copyProperties(etudiant,reponseDTO);
        return reponseDTO;
    }
}

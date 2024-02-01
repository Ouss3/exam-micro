package com.example.etudiant.services;


import com.example.etudiant.DTO.ReponseDTO;
import com.example.etudiant.DTO.RequestDTO;
import com.example.etudiant.entites.Etudiant;
import com.example.etudiant.mapper.MapperInterface;
import com.example.etudiant.repository.EtudientRepository;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Services implements ServicesInterface{

   EtudientRepository etudientRepository;
   MapperInterface mapperInterface;

    public Services(EtudientRepository etudientRepository, MapperInterface mapperInterface) {
        this.etudientRepository = etudientRepository;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public List<ReponseDTO> getAllEtudiants() {
        List<Etudiant> etudiantList = etudientRepository.findAll() ;
        List<ReponseDTO> reponseDTOList = new ArrayList<>();
        for (Etudiant etudiant: etudiantList) {
            reponseDTOList.add(mapperInterface.EtudiantToReponseDTO(etudiant));
        }

        return reponseDTOList;
    }

    @Override
    public ReponseDTO getEtudiantById(Integer id) {
        return mapperInterface.EtudiantToReponseDTO(etudientRepository.findById(id).get());

    }

    @Override
    public void addEtudiant(RequestDTO requestDTO) {
        Etudiant etudiant = mapperInterface.RequestToEtudiant(requestDTO);
        etudientRepository.save(etudiant);

    }

    @Override
    public void updateEtudiant(Integer id, RequestDTO requestDTO) {
        Etudiant etudiant = etudientRepository.findById(id).get();
        if (requestDTO.getNom() != null)
            etudiant.setNom(requestDTO.getNom());
        if (requestDTO.getPrenom() != null)
            etudiant.setPrenom(requestDTO.getPrenom());
        if (requestDTO.getEmail() != null)
            etudiant.setEmail(requestDTO.getEmail());
        if (requestDTO.getFiliere() != null)
            etudiant.setFiliere(requestDTO.getFiliere());

        etudientRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(Integer id) {
        etudientRepository.deleteById(id);
    }
}

package com.example.etudiant.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReponseDTO {
    private Integer idEtudiant;
    private String nom;
    private String prenom;
    private String email;
    private String token;
    private String password;



}

package com.example.etudiant.DTO;

import com.example.etudiant.entites.Filiere;
import lombok.*;

@Data@AllArgsConstructor@NoArgsConstructor@Builder@ToString
public class RequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private Filiere filiere;

}

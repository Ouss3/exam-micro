package com.example.emprunter.model;
import lombok.*;

@Data@AllArgsConstructor@NoArgsConstructor@ToString@Builder
public class Etudiant {
    public Integer idEtudiant;
    public String nom;
    public String prenom;
    public String email;

    public Filiere filiere;

}

package com.example.etudiant.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor@ToString@Builder
public class Etudiant {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idEtudiant;
    public String nom;
    public String prenom;
    public String email;
    @Enumerated(EnumType.STRING)
    public Filiere filiere;
}

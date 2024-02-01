package com.example.emprunter.entites;
import com.example.emprunter.model.Liver;
import com.example.emprunter.model.Etudiant;
import lombok.*;
import jakarta.persistence.*;

@Data@AllArgsConstructor@NoArgsConstructor@ToString@Builder@Entity
public class Emprunter {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprunter;
    private Integer idEtudiant;
    private Integer idLivre;
    @Transient
    private Liver liver;
    @Transient
    private Etudiant etudiant;


}

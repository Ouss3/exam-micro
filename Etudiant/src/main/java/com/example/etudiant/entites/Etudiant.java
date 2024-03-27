package com.example.etudiant.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiant")
@Data@AllArgsConstructor@NoArgsConstructor@ToString@Builder
public class Etudiant {
    @Id
    public String email;
    public String token;
    public String password;
    public boolean haveform;
    public String formcode;

}

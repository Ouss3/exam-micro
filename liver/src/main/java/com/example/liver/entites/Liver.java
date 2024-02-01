package com.example.liver.entites;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity@Data@AllArgsConstructor@NoArgsConstructor@Builder@ToString
public class Liver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Disciplines disciplines;

}

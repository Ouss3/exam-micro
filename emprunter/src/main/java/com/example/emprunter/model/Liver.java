package com.example.emprunter.model;

import lombok.*;
@Data@AllArgsConstructor@NoArgsConstructor@ToString@Builder
public class Liver {
    private Integer id;
    private String nom;

    private Disciplines disciplines;
}

package com.example.liver.Dto;


import com.example.liver.entites.Disciplines;
import lombok.*;
@Data@AllArgsConstructor@NoArgsConstructor@Builder@ToString
public class ReponseDTO {
    private Integer id;
    private String nom;

    private Disciplines disciplines;
}

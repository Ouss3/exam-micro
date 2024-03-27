package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Supervisor {

    public String svfname;

    public String svlname;

    public String  svmail;

    public String svinst;

}

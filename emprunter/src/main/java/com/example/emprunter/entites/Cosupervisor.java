package com.example.emprunter.entites;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cosupervisor {

    public String cosvfname;

    public String cosvlname;

    public String cosvmail;

    public String cosvinst;
}

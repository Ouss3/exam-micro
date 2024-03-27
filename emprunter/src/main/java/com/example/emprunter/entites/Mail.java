package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Mail {
    @XmlAttribute
    public String type;
    @XmlValue
    public String value;


}

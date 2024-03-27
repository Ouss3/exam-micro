package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Structure {
     @XmlValue
    public String value;
    @XmlAttribute
    public String type;

}

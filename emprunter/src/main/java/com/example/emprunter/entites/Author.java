package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Author{
    @XmlElement(name = "firstname")
    public String firstName;
    @XmlElement(name = "lastName")
    public String lastName;
    @XmlElement(name = "birth")
    public String birth;
    @XmlAttribute
    public String ApogeCode;
    @XmlAttribute
    public String sex;
    @XmlAttribute
    public int registrationYear;
}

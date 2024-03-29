package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor@ToString@Builder@XmlRootElement
public class Form {

//    public String id;
    @XmlElement
    public Author firstAuthor;
    @XmlElement
    public ArrayList<Author> authors;
    @XmlElement
    public String title;
    @XmlElement
    public String abstracts;
    @XmlElement
    public String keywords;
    @XmlElement
    public String disciplines;
    @XmlElement
    public String speciality;
    @XmlElement
    public String theme;
    @XmlElement
    public String institution;
    @XmlElement
    public Structure structure;
    @XmlElementWrapper(name="mails")
    @XmlElement(name="mail")
    public ArrayList<Mail>  mails = new ArrayList<Mail>();
    @XmlElement
    public String phone;
    @XmlElement
    public String address;
    @XmlElement
    public String ville;
    @XmlElement
    public Supervisor supervisor;
    @XmlElement
    public Cosupervisor cosupervisor;







}

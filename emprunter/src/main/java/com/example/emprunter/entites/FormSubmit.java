package com.example.emprunter.entites;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "formSubmit")
public class FormSubmit {
    private List<Form> forms;

    public FormSubmit() {
        forms = new ArrayList<>();
    }

    @XmlElement(name = "form")
    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
}
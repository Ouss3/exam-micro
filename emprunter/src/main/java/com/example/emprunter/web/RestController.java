package com.example.emprunter.web;

import com.example.emprunter.entites.Author;
import com.example.emprunter.entites.Form;
import com.example.emprunter.entites.FormSubmit;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {


   @PostMapping("/addform")
public Form addForm(@RequestBody Form form) throws JAXBException, IOException {
    JAXBContext context = JAXBContext.newInstance(FormSubmit.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    File file = new File("form.xml");
    FormSubmit formSubmit;
    if (file.exists()) {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        formSubmit = (FormSubmit) unmarshaller.unmarshal(file);
    } else {
        formSubmit = new FormSubmit();
    }

    formSubmit.getForms().add(form);

    marshaller.marshal(formSubmit, file);

    return form;
}


//    @GetMapping("/getform/{mail}")
//    public Form getForm(@PathVariable String mail)  {
//        try {
//            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("form.xml");
//            XPath xpath = XPathFactory.newInstance().newXPath();
//            String expression = "//form[mail1='" + mail + "']";
//            Node formNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
//            if (formNode != null) {
//                Form form = new Form();
//                NodeList childNodes = formNode.getChildNodes();
//                for (int i = 0; i < childNodes.getLength(); i++) {
//                    Node childNode = childNodes.item(i);
//                    switch (childNode.getNodeName()) {
//                        case "title":
//                            form.setTitle(childNode.getTextContent());
//                            break;
//                        case "abstracts":
//                            form.setAbstracts(childNode.getTextContent());
//                            break;
//                        case "keywords":
//                            form.setKeywords(childNode.getTextContent());
//                            break;
//                        case "disciplines":
//                            form.setDisciplines(childNode.getTextContent());
//                            break;
//                        case "speciality":
//                            form.setSpeciality(childNode.getTextContent());
//                            break;
//                        case "theme":
//                            form.setTheme(childNode.getTextContent());
//                            break;
//                        case "institution":
//                            form.setInstitution(childNode.getTextContent());
//                            break;
//                        case "structure":
//                            form.setStructure(childNode.getTextContenti ());
//                            break;
//                        case "mail1":
//                            form.setMail1(childNode.getTextContent());
//                            break;
//                        case "mail2":
//                            form.setMail2(childNode.getTextContent());
//                            break;
//                        case "phone":
//                            form.setPhone(childNode.getTextContent());
//                            break;
//                        case "address":
//                            form.setAddress(childNode.getTextContent());
//                            break;
//                        case "ville":
//                            form.setVille(childNode.getTextContent());
//                            break;
//                        case "svfname":
//                            form.setSvfname(childNode.getTextContent());
//                            break;
//                        case "svlname":
//                            form.setSvlname(childNode.getTextContent());
//                            break;
//                        case "svmail":
//                            form.setSvmail(childNode.getTextContent());
//                            break;
//                        case "svinst":
//                            form.setSvinst(childNode.getTextContent());
//                            break;
//                        case "cosvfname":
//                            form.setCosvfname(childNode.getTextContent());
//                            break;
//                        case "cosvlname":
//                            form.setCosvlname(childNode.getTextContent());
//                            break;
//                        case "cosvmail":
//                            form.setCosvmail(childNode.getTextContent());
//                            break;
//                        case "cosvinst":
//                            form.setCosvinst(childNode.getTextContent());
//                            break;
//                        case "authors":
//                            NodeList authorNodes = childNode.getChildNodes();
//                            Author author = new Author();
//                            for (int j = 0; j < authorNodes.getLength(); j++) {
//                                Node authorNode = authorNodes.item(j);
//                                switch (authorNode.getNodeName()) {
//                                    case "birth":
//                                        author.setBirth(authorNode.getTextContent());
//                                        break;
//                                    case "firstName":
//                                        author.setFirstName(authorNode.getTextContent());
//                                        break;
//                                    case "lastName":
//                                        author.setLastName(authorNode.getTextContent());
//                                        break;
//                                    case "registrationYear":
//                                        author.setRegistrationYear(Integer.parseInt(authorNode.getTextContent()));
//                                        break;
//                                    case "sex":
//                                        author.setSex(authorNode.getTextContent());
//                                        break;
//                                }
//                            }
//                            form.getAuthors().add(author);
//                            break;
//
//                    }
//                }
//                return form;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}







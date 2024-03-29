package com.example.emprunter.web;

import com.example.emprunter.entites.*;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {


   @PostMapping("/addform")
public Form addForm(@RequestBody Form form) throws JAXBException, IOException, TransformerException {
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



       transformXmlToTs();
       generateLatexFile();



       return form;
}



    @PutMapping("/updateform/{mail}")
    public Form updateForm(@PathVariable String mail, @RequestBody Form newForm) throws JAXBException, IOException, TransformerException {
        JAXBContext context = JAXBContext.newInstance(FormSubmit.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File("form.xml");
        FormSubmit formSubmit = (FormSubmit) unmarshaller.unmarshal(file);

        for (Form form : formSubmit.getForms()) {
            for (Mail formMail : form.mails) {
                if (formMail.value.equals(mail)) {
                    int index = formSubmit.getForms().indexOf(form);
                    formSubmit.getForms().set(index, newForm);
                    break;
                }
            }
        }

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(formSubmit, file);




        transformXmlToTs();
        generateLatexFile();

        return newForm;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        File file = new File("latex.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


    @GetMapping("/getform/{mail}")
    public Form getForm(@PathVariable String mail)  {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("form.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            String expression = "//form[mails/mail[text()='" + mail + "']]";
            Node formNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
            if (formNode != null) {
                Form form = new Form();
                NodeList childNodes = formNode.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node childNode = childNodes.item(i);
                    switch (childNode.getNodeName()) {
                        case "title":
                            form.title = childNode.getTextContent();
                            break;
                        case "abstracts":
                            form.abstracts = childNode.getTextContent();
                            break;
                        case "keywords":
                            form.keywords = childNode.getTextContent();
                            break;
                        case "disciplines":
                            form.disciplines = childNode.getTextContent();
                            break;
                        case "speciality":
                            form.speciality = childNode.getTextContent();
                            break;
                        case "theme":
                            form.theme = childNode.getTextContent();
                            break;
                        case "institution":
                            form.institution = childNode.getTextContent();
                            break;
                        case "structure":
                            Structure structure = new Structure();
                            structure.type = childNode.getAttributes().getNamedItem("type").getTextContent();
                            structure.value = childNode.getTextContent();
                            form.structure = structure;
                            break;
                        case "mails":
                            NodeList mailNodes = childNode.getChildNodes();
                            for (int j = 0; j < mailNodes.getLength(); j++) {
                                Node mailNode = mailNodes.item(j);
                                if ("mail".equals(mailNode.getNodeName())) {
                                    Mail mailObj = new Mail();
                                    mailObj.type = mailNode.getAttributes().getNamedItem("type").getTextContent();
                                    mailObj.value = mailNode.getTextContent();
                                    form.mails.add(mailObj);
                                }
                            }
                            break;
                        case "phone":
                            form.phone = childNode.getTextContent();
                            break;
                        case "address":
                            form.address = childNode.getTextContent();
                            break;
                        case "ville":
                            form.ville = childNode.getTextContent();
                            break;
                        case "supervisor":
                            Supervisor supervisor = new Supervisor();
                            NodeList supervisorChildNodes = childNode.getChildNodes();
                            for (int j = 0; j < supervisorChildNodes.getLength(); j++) {
                                Node supervisorChildNode = supervisorChildNodes.item(j);
                                switch (supervisorChildNode.getNodeName()) {
                                    case "svfname":
                                        supervisor.svfname = supervisorChildNode.getTextContent();
                                        break;
                                    case "svlname":
                                        supervisor.svlname = supervisorChildNode.getTextContent();
                                        break;
                                    case "svmail":
                                        supervisor.svmail = supervisorChildNode.getTextContent();
                                        break;
                                    case "svinst":
                                        supervisor.svinst = supervisorChildNode.getTextContent();
                                        break;
                                }
                            }
                            form.supervisor = supervisor;
                            break;
                        case "cosupervisor":
                            Cosupervisor cosupervisor = new Cosupervisor();
                            NodeList cosupervisorChildNodes = childNode.getChildNodes();
                            for (int j = 0; j < cosupervisorChildNodes.getLength(); j++) {
                                Node cosupervisorChildNode = cosupervisorChildNodes.item(j);
                                switch (cosupervisorChildNode.getNodeName()) {
                                    case "cosvfname":
                                        cosupervisor.cosvfname = cosupervisorChildNode.getTextContent();
                                        break;
                                    case "cosvlname":
                                        cosupervisor.cosvlname = cosupervisorChildNode.getTextContent();
                                        break;
                                    case "cosvmail":
                                        cosupervisor.cosvmail = cosupervisorChildNode.getTextContent();
                                        break;
                                    case "cosvinst":
                                        cosupervisor.cosvinst = cosupervisorChildNode.getTextContent();
                                        break;
                                }
                            }
                            form.cosupervisor = cosupervisor;
                            break;

                        case "firstAuthor":
                            Author firstAuthor = new Author();
                            NodeList authorChildNodesF = childNode.getChildNodes();
                            for (int j = 0; j < authorChildNodesF.getLength(); j++) {
                                Node authorChildNode = authorChildNodesF.item(j);
                                switch (authorChildNode.getNodeName()) {
                                    case "firstname":
                                        firstAuthor.firstName = authorChildNode.getTextContent();
                                        break;
                                    case "lastName":
                                        firstAuthor.lastName = authorChildNode.getTextContent();
                                        break;
                                    case "birth":
                                        firstAuthor.birth = authorChildNode.getTextContent();
                                        break;
                                }
                            }
                            firstAuthor.ApogeCode = childNode.getAttributes().getNamedItem("apogeCode").getTextContent();
                            firstAuthor.sex = childNode.getAttributes().getNamedItem("sex").getTextContent();
                            firstAuthor.registrationYear = Integer.parseInt(childNode.getAttributes().getNamedItem("registrationYear").getTextContent());
                            form.firstAuthor = firstAuthor;
                            break;
                        case "authors":
                            NodeList authorNodes = childNode.getChildNodes();
                            for (int j = 0; j < authorNodes.getLength(); j++) {
                                Node authorNode = authorNodes.item(j);
                                if ("author".equals(authorNode.getNodeName())) {
                                    Author author = new Author();
                                    NodeList authorChildNodes = authorNode.getChildNodes();
                                    for (int k = 0; k < authorChildNodes.getLength(); k++) {
                                        Node authorChildNode = authorChildNodes.item(k);
                                        switch (authorChildNode.getNodeName()) {
                                            case "firstname":
                                                author.firstName = authorChildNode.getTextContent();
                                                break;
                                            case "lastName":
                                                author.lastName = authorChildNode.getTextContent();
                                                break;
                                            case "birth":
                                                author.birth = authorChildNode.getTextContent();
                                                break;
                                        }
                                    }
                                    author.ApogeCode = authorNode.getAttributes().getNamedItem("ApogeCode").getTextContent();
                                    author.sex = authorNode.getAttributes().getNamedItem("sex").getTextContent();
                                    author.registrationYear = Integer.parseInt(authorNode.getAttributes().getNamedItem("registrationYear").getTextContent());
                                    form.authors.add(author);
                                }
                            }
                            break;
                    }
                }
                return form;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void generateLatexFile() throws TransformerException, IOException {
        File xmlFile = new File("form.xml");
        File xslFile = new File("pdf.xsl");

        StreamSource xmlSource = new StreamSource(xmlFile);
        StreamSource xslSource = new StreamSource(xslFile);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);

        StringWriter writer = new StringWriter();
        transformer.transform(xmlSource, new StreamResult(writer));

        String output = writer.toString();
        Files.write(Paths.get("latex.tex"), output.getBytes());
    }

    public void transformXmlToTs() throws TransformerException, IOException {
        File file = new File("form.xml");
        StreamSource xmlSource = new StreamSource(file);
        StreamSource xslSource = new StreamSource(new File("form.xsl"));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);
        StringWriter writer = new StringWriter();
        transformer.transform(xmlSource, new StreamResult(writer));
        String output = writer.toString();
        Files.write(Paths.get("xml/xml_project/src/app/calendar/calendar.component.ts"), output.getBytes());

    }

}







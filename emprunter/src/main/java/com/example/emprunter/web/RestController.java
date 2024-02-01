package com.example.emprunter.web;

import com.example.emprunter.entites.Emprunter;
import com.example.emprunter.etudiant.EtudiantOF;
import com.example.emprunter.liver.LiverOF;
import com.example.emprunter.model.Etudiant;
import com.example.emprunter.model.Liver;
import com.example.emprunter.repositry.EmprunterRepositry;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {

    private EtudiantOF etudiantOF;
    private LiverOF liverOF;
    private EmprunterRepositry emprunterRepositry;

    @GetMapping("/emprunter")
    public List<Emprunter> getAll(){
        List<Emprunter> emprunters = emprunterRepositry.findAll();
        for (   Emprunter e:emprunters)
              {
                  Etudiant etudiant = etudiantOF.getEtudiantById(e.getIdEtudiant());
                  Liver liver = liverOF.getbyid(e.getIdLivre());
                    e.setEtudiant(etudiant);
                    e.setLiver(liver);
              }
            return emprunters;
        }

        @GetMapping("/emprunter/{id}")
        public Emprunter getbyid(@PathVariable Integer id){
            Emprunter emprunter = emprunterRepositry.findById(id).get();
            Etudiant etudiant = etudiantOF.getEtudiantById(emprunter.getIdEtudiant());
            Liver liver = liverOF.getbyid(emprunter.getIdLivre());
            emprunter.setEtudiant(etudiant);
            emprunter.setLiver(liver);
            return emprunter;
        }


        @PostMapping("/emprunter")
          public void save(@RequestBody Emprunter emprunter){
                emprunterRepositry.save(Emprunter.builder()
                        .idEtudiant(emprunter.getIdEtudiant())
                        .idLivre(emprunter.getIdLivre())
                        .build());
            }

            @PutMapping("/emprunter/{id}")
            public void update(@PathVariable Integer id, @RequestBody Emprunter emprunter){
              Emprunter emprunter1 = emprunterRepositry.findById(id).get();
              if (emprunter.getIdEtudiant()!=null)
                  emprunter1.setIdEtudiant(emprunter.getIdEtudiant());
                if (emprunter.getIdLivre()!=null)
                    emprunter1.setIdLivre(emprunter.getIdLivre());
                emprunterRepositry.save(emprunter1);
            }

          @DeleteMapping("/emprunter/{id}")
            public void delete(@PathVariable Integer id){
                    emprunterRepositry.deleteById(id);
                }



    }



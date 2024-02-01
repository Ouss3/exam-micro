package com.example.liver.web;


import com.example.liver.Dto.ReponseDTO;
import com.example.liver.Dto.RequestDTO;
import com.example.liver.servies.ServicesInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {
    private ServicesInterface servicesInterface;


    @GetMapping("/liver")
   public List<ReponseDTO> getAll(){
        return servicesInterface.getAll();
    }

    @GetMapping("/liver/{id}")
  public ReponseDTO getbyid(@PathVariable Integer id){
        return servicesInterface.getOne(id);
    }

    @PutMapping("/liver/{id}")
    public ReponseDTO update(@PathVariable Integer id , RequestDTO requestDTO){
        return servicesInterface.update(requestDTO,id);
    }

    @PostMapping("/liver")
    public ReponseDTO save(RequestDTO requestDTO){
        return servicesInterface.save(requestDTO);
    }

    @DeleteMapping("/liver/{id}")
    public void delete(@PathVariable Integer id){
        servicesInterface.delete(id);
    }
}

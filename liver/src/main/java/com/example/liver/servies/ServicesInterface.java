package com.example.liver.servies;



import com.example.liver.Dto.ReponseDTO;
import com.example.liver.Dto.RequestDTO;

import java.util.List;

public interface ServicesInterface {

    List<ReponseDTO> getAll();
    ReponseDTO getOne(Integer id);
    ReponseDTO save(RequestDTO reponseDTO);
    ReponseDTO update(RequestDTO reponseDTO, Integer id);
    void delete(Integer id);

}

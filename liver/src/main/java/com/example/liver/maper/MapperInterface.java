package com.example.liver.maper;


import com.example.liver.Dto.ReponseDTO;
import com.example.liver.Dto.RequestDTO;
import com.example.liver.entites.Liver;

public interface MapperInterface {
   Liver requestToLiver(RequestDTO requestDTO);
    ReponseDTO liverToReponse(Liver prof);
}

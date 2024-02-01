package com.example.liver.maper;



import com.example.liver.Dto.ReponseDTO;
import com.example.liver.Dto.RequestDTO;
import com.example.liver.entites.Liver;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements MapperInterface{
    @Override
    public Liver requestToLiver(RequestDTO requestDTO) {

        return Liver.builder()
                .nom(requestDTO.getNom())
                .disciplines(requestDTO.getDisciplines())
                .build();
    }

    @Override
    public ReponseDTO liverToReponse(Liver prof) {

        return ReponseDTO.builder()
                .id(prof.getId())
                .nom(prof.getNom())
                .disciplines(prof.getDisciplines())
                .build();
    }
}

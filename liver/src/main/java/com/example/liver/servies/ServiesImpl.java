package com.example.liver.servies;




import com.example.liver.Dto.ReponseDTO;
import com.example.liver.Dto.RequestDTO;
import com.example.liver.entites.Liver;
import com.example.liver.maper.MapperInterface;
import com.example.liver.repository.LiverRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiesImpl implements ServicesInterface  {
   LiverRepository profRepository;
    MapperInterface mapperInterface;

    public ServiesImpl(LiverRepository profRepository, MapperInterface mapperInterface) {
        this.profRepository = profRepository;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public List<ReponseDTO> getAll() {
       List<Liver> list = profRepository.findAll();
       List<ReponseDTO> list1 = new ArrayList<>();
       for (Liver liver : list){
           list1.add(mapperInterface.liverToReponse(liver));
       }
         return list1;

    }

    @Override
    public ReponseDTO getOne(Integer id) {
       Liver liver = profRepository.findById(id).get();
               return mapperInterface.liverToReponse(liver);
    }

    @Override
    public ReponseDTO save(RequestDTO requestDTO) {
        Liver prof = mapperInterface.requestToLiver(requestDTO);
        profRepository.save(prof);
        return mapperInterface.liverToReponse(prof);
    }

    @Override
    public ReponseDTO update(RequestDTO reponseDTO, Integer id) {
      Liver liver = profRepository.findById(id).get();
         if (reponseDTO.getNom() != null)
              liver.setNom(reponseDTO.getNom());
            if (reponseDTO.getDisciplines() != null)
                liver.setDisciplines(reponseDTO.getDisciplines());
            profRepository.save(liver);
            return mapperInterface.liverToReponse(liver);


    }

    @Override
    public void delete(Integer id) {
        profRepository.deleteById(id);

    }
}

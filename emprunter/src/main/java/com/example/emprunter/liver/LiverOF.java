package com.example.emprunter.liver;

import com.example.emprunter.model.Liver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("LIVER-SERVICE")
public interface LiverOF {
    @GetMapping("/api/liver")
    public List<Liver> getAll();

    @GetMapping("/api/liver/{id}")
    public Liver getbyid(@PathVariable Integer id);

}

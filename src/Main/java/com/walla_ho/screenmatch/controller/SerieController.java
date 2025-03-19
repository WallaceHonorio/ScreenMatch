package com.walla_ho.screenmatch.controller;

import com.walla_ho.screenmatch.dto.SerieDTO;
import com.walla_ho.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> getSeries(){
        return repository.findAll()
                .stream()
                .map(s -> new SerieDTO (s.getId(), s.getTitle(), s.getYear(), s.getReleased(), s.getGender(), s.getTotalSeasons(), s.getImdbRating(), s.getActors(), s.getSummary(), s.getPoster()))
                .collect(Collectors.toList());
    }
}

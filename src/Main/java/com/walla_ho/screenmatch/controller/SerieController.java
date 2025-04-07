package com.walla_ho.screenmatch.controller;

import com.walla_ho.screenmatch.dto.EpisodeDTO;
import com.walla_ho.screenmatch.dto.SerieDTO;
import com.walla_ho.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    SerieService service;

    @GetMapping
    public List<SerieDTO> getSeries(){
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series(){
        return service.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> getReleasedSeries(){
        return service.getTop5Released();
    }

    @GetMapping("/starter")
    public String returnStarter() {
        return "Welcome to Screenmatch!";
    }

    @GetMapping("/{id}")
    public SerieDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeason(@PathVariable Long id) {
        return service.getAllSeason(id);
    }

    @GetMapping("/{id}/temporadas/{season}")
    public List<EpisodeDTO> getSeasonById(@PathVariable Long id, @PathVariable Long season) {
        return service.getSeasonById(id, season);
    }

    @GetMapping("/categoria/{nameCategory}")
    public List<SerieDTO> getSeriesByCategory(@PathVariable String nameCategory) {
        return service.getSeriesByCategory(nameCategory);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodeDTO> getTop5Episodes(@PathVariable Long id) {
        return service.getTop5Episode(id);
    }
}

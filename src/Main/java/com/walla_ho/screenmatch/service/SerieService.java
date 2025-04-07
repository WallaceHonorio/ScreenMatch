package com.walla_ho.screenmatch.service;

import com.walla_ho.screenmatch.dto.EpisodeDTO;
import com.walla_ho.screenmatch.dto.SerieDTO;
import com.walla_ho.screenmatch.model.Category;
import com.walla_ho.screenmatch.model.Serie;
import com.walla_ho.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> getAllSeries(){
        return convertSerieDTO(repository.findAll());
    }

    public List<SerieDTO> getTop5Series() {
        return convertSerieDTO(repository.findTop5ByOrderByImdbRating());
    }

    private List<SerieDTO> convertSerieDTO(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO (s.getId(), s.getTitle(), s.getYear(), s.getReleased(), s.getGender(), s.getTotalSeasons(), s.getImdbRating(), s.getActors(), s.getSummary(), s.getPoster()))
                .collect(Collectors.toList());
    }


    public List<SerieDTO> getTop5Released() {
        return convertSerieDTO(repository.findEpisodesMoreRecent());
    }

    public SerieDTO getById(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO( s.getId(), s.getTitle(),
                                 s.getYear(), s.getReleased(),
                                 s.getGender(), s.getTotalSeasons(),
                                 s.getImdbRating(), s.getActors(),
                                 s.getSummary(), s.getPoster());
        }

        //if not
        return null;
    }

    public List<EpisodeDTO> getAllSeason(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(e -> new EpisodeDTO(e.getSeason(), e.getEpisode(), e.getTitle()))
                    .collect(Collectors.toList());
        }

        //if not
        return null;
    }

    public List<EpisodeDTO> getSeasonById(Long id, Long season){
        return repository.findEpisodesBySeason(id, season)
                .stream()
                .map(e -> new EpisodeDTO(e.getSeason(), e.getEpisode(), e.getTitle()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> getSeriesByCategory(String nameCategory) {
        Category category = Category.fromString(nameCategory);
        return convertSerieDTO(repository.findByGender(category));
    }
}

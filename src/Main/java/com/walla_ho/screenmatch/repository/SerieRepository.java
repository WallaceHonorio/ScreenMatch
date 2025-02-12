package com.walla_ho.screenmatch.repository;

import com.walla_ho.screenmatch.model.Category;
import com.walla_ho.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie>findByTitleContainingIgnoreCase(String serieName);

    List<Serie> findByActorsContainingIgnoreCaseAndImdbRatingGreaterThanEqual(String actorName, double rating);

    List<Serie> findTop5ByOrderByImdbRating();

    List<Serie> findByGender(Category category);

    List<Serie> findByTotalSeasonsLessThanEqualAndImdbRatingGreaterThanEqual(int totalSeasons, double imdbRating);

    //@Query(value = "select * from series WHERE series.totalSeasons <= :totalSeasons AND series.imdbRating >= :imdbRating", nativeQuery = true)
    @Query(value = "select s from Serie s WHERE s.totalSeasons <= :totalSeasons AND s.imdbRating >= :imdbRating")
    List<Serie> serieByTotalSeasonsAndImdbRating(int totalSeasons, double imdbRating);

}

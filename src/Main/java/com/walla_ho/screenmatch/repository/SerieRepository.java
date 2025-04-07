package com.walla_ho.screenmatch.repository;

import com.walla_ho.screenmatch.model.Category;
import com.walla_ho.screenmatch.model.Episode;
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

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:excerptEpisode%")
    List<Episode> episodeByExcerpt(String excerptEpisode);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.imdbRating DESC LIMIT 5")
    List<Episode> topEpisodesBySerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie AND YEAR(e.released) >= :yearRelease")
    List<Episode> episodeBySerieANDYear(Serie serie, int yearRelease);

    List<Serie> findTop5ByOrderByEpisodesReleasedDesc();

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodes e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.released) DESC LIMIT 5")
    List<Serie> findEpisodesMoreRecent();


    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s.id = :id AND e.season = :season")
    List<Episode> findEpisodesBySeason(Long id, Long season);


    @Query("SELECT e FROM Serie s JOIN s.episodes e ORDER BY e.imdbRating DESC LIMIT 5")
    List<Episode> getTop5Episode(Long id);
}

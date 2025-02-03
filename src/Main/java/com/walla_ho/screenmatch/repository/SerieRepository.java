package com.walla_ho.screenmatch.repository;

import com.walla_ho.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie>findBytitleContainingIgnoreCase(String serieName);

}

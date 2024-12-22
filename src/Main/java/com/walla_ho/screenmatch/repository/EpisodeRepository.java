package com.walla_ho.screenmatch.repository;

import com.walla_ho.screenmatch.model.Episode;
import com.walla_ho.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}

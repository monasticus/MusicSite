package com.musicsite.repository;

import com.musicsite.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformerRepository extends JpaRepository<Performer, Long> {

    Performer getFirstPerformerByPseudonymIgnoreCase(String pseudonym);
    List<Performer> getPerformersByPseudonymIgnoreCase(String pseudonym);
    List<Performer> getPerformersByPropositionFalseOrderByAverageDesc();

}

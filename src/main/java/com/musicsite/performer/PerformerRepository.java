package com.musicsite.performer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformerRepository extends JpaRepository<Performer, Long> {

    Performer getFirstPerformerByPseudonymIgnoreCase(String pseudonym);

    List<Performer> getPerformersByPseudonymIgnoreCase(String pseudonym);

    List<Performer> getPerformersByPropositionFalseOrderByAverageDesc();

    List<Performer> getPerformersByPropositionTrue();

    @Query("SELECT p FROM Performer p WHERE LOWER(p.pseudonym) like LOWER(concat('%', :part, '%') ) AND p.proposition = 0")
    List<Performer> customGetPerformersByQuery(@Param("part") String part);

}

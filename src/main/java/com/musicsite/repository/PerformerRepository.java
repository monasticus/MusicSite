package com.musicsite.repository;

import com.musicsite.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformerRepository extends JpaRepository<Performer, Long> {

    List<Performer> getPerformersByPseudonym(String pseudonym);
}

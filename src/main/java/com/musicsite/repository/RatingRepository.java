package com.musicsite.repository;

import com.musicsite.entity.Performer;
import com.musicsite.entity.Rating;
import com.musicsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating getRatingByUserAndPerformer(User user, Performer performer);
}

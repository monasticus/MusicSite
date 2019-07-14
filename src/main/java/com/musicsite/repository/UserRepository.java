package com.musicsite.repository;

import com.musicsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmailIgnoreCase(String email);
}

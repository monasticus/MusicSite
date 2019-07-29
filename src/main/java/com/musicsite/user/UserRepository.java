package com.musicsite.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmailIgnoreCase(String email);

    User getUserByUsernameIgnoreCase(String username);
}

package com.sunny.musicplayer.musicplayer.repository;

import com.sunny.musicplayer.musicplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

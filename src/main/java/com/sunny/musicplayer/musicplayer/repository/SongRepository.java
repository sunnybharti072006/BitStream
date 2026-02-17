package com.sunny.musicplayer.musicplayer.repository;

import com.sunny.musicplayer.musicplayer.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}

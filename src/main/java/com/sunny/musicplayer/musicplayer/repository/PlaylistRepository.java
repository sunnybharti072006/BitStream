package com.sunny.musicplayer.musicplayer.repository;

import com.sunny.musicplayer.musicplayer.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}

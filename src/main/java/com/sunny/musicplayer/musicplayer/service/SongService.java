package com.sunny.musicplayer.musicplayer.service;

import com.sunny.musicplayer.musicplayer.model.Song;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface SongService {

    List<Song> getAllSongs();

    Song addSong(Song song);

    Song getSongById(Long id);

    Song updateSong(Long id, Song song);

    void deleteSong(Long id);

    List<Song> searchSong(String query);

    boolean toggleLike(Long userId, Long songId);

    Song playSong(Long id);

    Song saveSong(Song song);
    Song unlikeSong(Long userId, Long songId);
    List<Song> getAllSongsuser();
    Set<Song> getUserLikedSongs(Long userId);
    Song uploadSong(Long userId, MultipartFile file,
                    String title,
                    String genre
    ) throws IOException;
    List<Song> getSongsBySinger(Long singerId);
    Song incrementPlayCount(Long id);
}
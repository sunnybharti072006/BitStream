package com.sunny.musicplayer.musicplayer.service;

import com.sunny.musicplayer.musicplayer.model.Song;
import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
    Song addSong(Song song);
    Song getSongById(Long id);
    Song saveSong(Song song);

}

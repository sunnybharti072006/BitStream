package com.sunny.musicplayer.musicplayer.service;

import com.sunny.musicplayer.musicplayer.model.Playlist;
import com.sunny.musicplayer.musicplayer.model.Song;

import java.util.List;

public interface PlaylistService {

    Playlist savePlaylist(Playlist playlist);

    Playlist getPlaylistById(Long id);

    List<Playlist> getAllPlaylists();

    void deletePlaylist(Long id);
    Song likeSong(Long playlistId, Long songId);
}
package com.sunny.musicplayer.musicplayer.service.impl;

import com.sunny.musicplayer.musicplayer.model.Playlist;
import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.repository.PlaylistRepository;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;
import com.sunny.musicplayer.musicplayer.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }


    @Override
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(";dvm not found with id " + id));
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Song likeSong(Long playlistId, Long songId) {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        playlist.getSongs().add(song);

        playlistRepository.save(playlist);

        return song;
    }
}

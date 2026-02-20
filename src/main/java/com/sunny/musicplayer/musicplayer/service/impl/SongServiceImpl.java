package com.sunny.musicplayer.musicplayer.service.impl;

import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;
import com.sunny.musicplayer.musicplayer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song getSongById(Long id){
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
    }
    @Override
    public Song updateSong(Long id, Song song) {

        Song existingSong = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));

        existingSong.setTitle(song.getTitle());
        existingSong.setArtist(song.getArtist());
        existingSong.setAlbum(song.getAlbum());
        existingSong.setFilePath(song.getFilePath());

        return songRepository.save(existingSong);
    }
    @Override
    public List<Song> searchSong(String query){
        return songRepository.findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCaseOrAlbumContainingIgnoreCase(
                query, query, query
        );
    }


}

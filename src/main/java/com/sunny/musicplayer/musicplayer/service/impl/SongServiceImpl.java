package com.sunny.musicplayer.musicplayer.service.impl;

import com.sunny.musicplayer.musicplayer.model.Role;
import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.model.User;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;
import com.sunny.musicplayer.musicplayer.repository.UserRepository;
import com.sunny.musicplayer.musicplayer.service.SongService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public SongServiceImpl(SongRepository songRepository,
                           UserRepository userRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }
    @Value("${music.folder.path}")
    private String musicFolderPath;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found with id: " + id));
    }

    @Override
    public Song updateSong(Long id, Song song) {

        Song existingSong = getSongById(id);

        existingSong.setTitle(song.getTitle());
        existingSong.setArtist(song.getArtist());
        existingSong.setAlbum(song.getAlbum());
        existingSong.setFilePath(song.getFilePath());

        return songRepository.save(existingSong);
    }

    @Override
    public void deleteSong(Long id) {
        Song song = getSongById(id);
        songRepository.delete(song);
    }

    @Override
    public List<Song> searchSong(String query) {
        return songRepository
                .findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCaseOrAlbumContainingIgnoreCase(
                        query, query, query
                );
    }

    @Override
    @Transactional
    public boolean toggleLike(Long userId, Long songId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (user.getLikedSongs().contains(song)) {
            user.getLikedSongs().remove(song);
            return false;
        } else {
            user.getLikedSongs().add(song);
            return true;
        }
    }
    @Override
    @Transactional
    public Song unlikeSong(Long userId, Long songId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        user.getLikedSongs().remove(song);

        return song;
    }

    @Override
    public Song playSong(Long id) {
        return getSongById(id);
    }

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongsuser() {
        return songRepository.findAll();
    }

    @Override
    @Transactional
    public Set<Song> getUserLikedSongs(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getLikedSongs();
    }

    @Override
    public Song uploadSong(Long userId,
                           MultipartFile file,
                           String title,
                           String genre) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.SINGER) {
            throw new RuntimeException("Only Singer can upload songs!");
        }

        File folder = new File(musicFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(folder, fileName);

        file.transferTo(destination);

        Song song = new Song();
        song.setTitle(title);
        song.setGenre(genre);
        song.setFilePath(destination.getAbsolutePath());
        song.setSinger(user);

        return songRepository.save(song);
    }
    @Override
    public List<Song> getSongsBySinger(Long singerId) {
        return songRepository.findBySingerId(singerId);
    }
    @Override
    @Transactional
    public Song incrementPlayCount(Long id) {

        Song song = getSongById(id);

        if (song.getPlayCount() == null) {
            song.setPlayCount(0L);
        }

        song.setPlayCount(song.getPlayCount() + 1);

        return songRepository.save(song);
    }

}

package com.sunny.musicplayer.musicplayer.Controller;

import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.service.SongService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @Value("${music.folder.path}")
    private String basePath;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song savedSong = songService.addSong(song);
        return ResponseEntity.ok(savedSong);
    }

    @GetMapping
    public List<Song> getSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/play/{id}")
    public ResponseEntity<Resource> playSong(@PathVariable Long id) throws IOException {

        Song song = songService.getSongById(id);

        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(basePath + File.separator + song.getFilePath());

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(file.toURI());

        String contentType = java.nio.file.Files.probeContentType(file.toPath());

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        return songService.updateSong(id, song);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSongs(@RequestParam String query) {
        List<Song> result = songService.searchSong(query);
        return ResponseEntity.ok(result);
    }

}
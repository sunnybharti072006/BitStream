package com.sunny.musicplayer.musicplayer.Controller;

import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.service.SongService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "*")
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

        if (song == null || song.getFilePath() == null) {
            return ResponseEntity.notFound().build();
        }

     File file = new File(song.getFilePath());

        if (!file.exists()) {
            file = new File(basePath + File.separator + song.getFilePath());
        }

        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath()); // Debugging line
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(file.toURI());
        String contentType = java.nio.file.Files.probeContentType(file.toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{songId}/like")
    public ResponseEntity<Boolean> toggleLike(
            @PathVariable Long songId,
            @RequestParam Long userId) {

        boolean liked = songService.toggleLike(userId, songId);
        return ResponseEntity.ok(liked);
    }

    @DeleteMapping("/{songId}/like")
    public ResponseEntity<Void> unlikeSong(
            @PathVariable Long songId,
            @RequestParam Long userId) {

        songService.unlikeSong(userId, songId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallSong")
    public ResponseEntity<List<Song>> getAllSonguser() {
        return ResponseEntity.ok(songService.getAllSongsuser());
    }

    @GetMapping("/user/{userId}/liked")
    public ResponseEntity<Set<Song>> getUserLikedSongs(@PathVariable Long userId) {
        return ResponseEntity.ok(songService.getUserLikedSongs(userId));
    }

    @PostMapping("/upload")
    public ResponseEntity<Song> uploadSong(
            @RequestParam("userId") Long userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("genre") String genre
    ) throws IOException {

        Song song = songService.uploadSong(userId, file, title, genre);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/singer/{id}")
    public ResponseEntity<List<Song>> getSongsBySinger(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongsBySinger(id));
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<?> incrementPlay(@PathVariable Long id, @RequestParam Long userId) {
        songService.incrementPlayCount(id);
        return ResponseEntity.ok("Play count updated");
    }
}

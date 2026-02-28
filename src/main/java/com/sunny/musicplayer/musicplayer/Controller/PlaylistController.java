package com.sunny.musicplayer.musicplayer.Controller;

import com.sunny.musicplayer.musicplayer.model.Playlist;
import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // Create new playlist
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlaylist);
    }

    // Get all playlists
    @GetMapping
    public ResponseEntity<?> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    // Get playlist by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id){
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    // Delete playlist
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylistById(@PathVariable Long id){
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok(";dvm deleted successfully");
    }
    @PostMapping("/{playlistId}/like")
    public ResponseEntity<Song> likeSong(
            @PathVariable Long playlistId,
            @RequestParam Long songId) {

        Song likedSong = playlistService.likeSong(playlistId, songId);

        return ResponseEntity.ok(likedSong);
    }

}

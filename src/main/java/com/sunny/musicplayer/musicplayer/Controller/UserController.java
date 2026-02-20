package com.sunny.musicplayer.musicplayer.Controller;

import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.model.User;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;
import com.sunny.musicplayer.musicplayer.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;
    private final SongRepository songRepo;

    public UserController(UserRepository userRepo, SongRepository songRepo) {
        this.userRepo = userRepo;
        this.songRepo = songRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check agar email pehle se exist karti hai
        if (userRepo.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered!");
        }
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepo.findByEmail(loginUser.getEmail());

        // Check user existence and password
        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or Password ");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/like/{songId}")
    public ResponseEntity<?> likeSong(@PathVariable Long userId, @PathVariable Long songId) {
        try {
            User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Song song = songRepo.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));

            user.getLikedSongs().add(song);
            userRepo.save(user);
            return ResponseEntity.ok("Song Liked ❤️");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/unlike/{songId}")
    public ResponseEntity<?> unlikeSong(@PathVariable Long userId, @PathVariable Long songId) {
        try {
            User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Song song = songRepo.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));

            user.getLikedSongs().remove(song);
            userRepo.save(user);
            return ResponseEntity.ok("Song Unliked 💔");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/liked")
    public ResponseEntity<?> getLikedSongs(@PathVariable Long userId) {
        return userRepo.findById(userId)
                .map(user -> ResponseEntity.ok(user.getLikedSongs()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
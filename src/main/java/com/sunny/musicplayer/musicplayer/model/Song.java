package com.sunny.musicplayer.musicplayer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"likedByUsers", "playlists"})
@EqualsAndHashCode(exclude = {"likedByUsers", "playlists"})
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String artist;
    private String album;

    @Column(name = "file_path")
    private String filePath;

    private String genre;

    @Column(nullable = false)
    private Long playCount = 0L;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "singer_id")
    @JsonIgnoreProperties({"likedSongs", "uploadedSongs"})
    private User singer;

    @JsonIgnore
    @ManyToMany(mappedBy = "likedSongs")
    private Set<User> likedByUsers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private Set<Playlist> playlists = new HashSet<>();
}
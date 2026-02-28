package com.sunny.musicplayer.musicplayer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunny.musicplayer.musicplayer.model.Song;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_liked_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> likedSongs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private Set<Song> uploadedSongs = new HashSet<>();
}
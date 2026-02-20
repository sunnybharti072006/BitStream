package com.sunny.musicplayer.musicplayer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "likedByUsers")
@EqualsAndHashCode(exclude = "likedByUsers")
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

    @JsonBackReference
    @ManyToMany(mappedBy = "likedSongs", fetch = FetchType.LAZY)
    private Set<User> likedByUsers = new HashSet<>();
}

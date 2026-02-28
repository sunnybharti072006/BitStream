package com.sunny.musicplayer.musicplayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongRequestDTO {
    private String title;
    private String artist;
    private String genre;

}

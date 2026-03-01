package com.sunny.musicplayer.musicplayer.dto;

import com.sunny.musicplayer.musicplayer.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private String userName;
    private int likeCount;
    private List<CommentResponseDto> replies;

    public static CommentResponseDto fromEntity(Comment c) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(c.getId());
        dto.setContent(c.getContent());
        dto.setUserName(c.getUser().getName());
        dto.setLikeCount(c.getLikedByUsers().size()); // <--- important
        if (!c.getReplies().isEmpty()) {
            dto.setReplies(c.getReplies().stream()
                    .map(CommentResponseDto::fromEntity)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
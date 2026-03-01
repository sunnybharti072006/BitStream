package com.sunny.musicplayer.musicplayer.service;

import com.sunny.musicplayer.musicplayer.dto.CommentResponseDto;
import com.sunny.musicplayer.musicplayer.dto.UserDto;
import com.sunny.musicplayer.musicplayer.model.Comment;
import com.sunny.musicplayer.musicplayer.model.Song;
import com.sunny.musicplayer.musicplayer.model.User;
import com.sunny.musicplayer.musicplayer.repository.CommentRepository;
import com.sunny.musicplayer.musicplayer.repository.SongRepository;
import com.sunny.musicplayer.musicplayer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          SongRepository songRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long userId, Long songId, String content, Long parentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setSong(song);

        if (parentId != null) {
            Comment parent = commentRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));

            if (!parent.getSong().getId().equals(songId)) {
                throw new RuntimeException("Reply must belong to same song");
            }

            comment.setParent(parent);
        }

        return commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsBySong(Long songId) {

        List<Comment> comments =
                commentRepository.findBySongIdAndParentIsNullOrderByCreatedAtDesc(songId);

        return comments.stream()
                .map(this::mapToDto)
                .toList();
    }

    private CommentResponseDto mapToDto(Comment comment) {

        UserDto userDto = new UserDto(
                comment.getUser().getId(),
                comment.getUser().getName()
        );

        List<CommentResponseDto> replyDtos = comment.getReplies()
                .stream()
                .map(this::mapToDto)
                .toList();

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                userDto,
                replyDtos,
                comment.getLikedByUsers().size()
        );
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("User not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public boolean toggleLike(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (comment.getLikedByUsers().contains(user)) {
            comment.getLikedByUsers().remove(user);
            return false; // unliked
        } else {
            comment.getLikedByUsers().add(user);
            return true; // liked
        }


    }

}
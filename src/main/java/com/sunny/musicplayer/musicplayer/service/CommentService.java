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
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          SongRepository songRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public Comment addComment(Long userId, Long songId, String content, Long parentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setSong(song);
        comment.setContent(content);

        if (parentId != null) {
            Comment parent = commentRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            comment.setParent(parent);
        }

        return commentRepository.save(comment);
    }

    public boolean toggleLike(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (comment.getLikedUsers().contains(user)) {
            comment.getLikedUsers().remove(user);
            commentRepository.save(comment);
            return false; // unliked
        } else {
            comment.getLikedUsers().add(user);
            commentRepository.save(comment);
            return true; // liked
        }
    }

    public List<CommentResponseDto> getCommentsBySong(Long songId) {
        List<Comment> comments = commentRepository.findBySongIdWithLikes(songId);
        return comments.stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to delete this comment");
        }

        commentRepository.delete(comment);
    }
    public int getLikeCount(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return comment.getLikedUsers() != null ? comment.getLikedUsers().size() : 0;
    }
}
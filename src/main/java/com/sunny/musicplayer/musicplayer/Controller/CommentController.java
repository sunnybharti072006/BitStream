package com.sunny.musicplayer.musicplayer.Controller;

import com.sunny.musicplayer.musicplayer.dto.CommentRequestDto;
import com.sunny.musicplayer.musicplayer.dto.CommentResponseDto;
import com.sunny.musicplayer.musicplayer.model.Comment;
import com.sunny.musicplayer.musicplayer.repository.CommentRepository;
import com.sunny.musicplayer.musicplayer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{songId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long songId,
            @RequestBody CommentRequestDto request) {

        return ResponseEntity.ok(
                commentService.addComment(
                        request.getUserId(),
                        songId,
                        request.getContent(),
                        request.getParentId()
                )
        );
    }

    @GetMapping("/{songId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long songId) {

        return ResponseEntity.ok(
                commentService.getCommentsBySong(songId)
        );
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Map<String,Object>> toggleLike(
            @PathVariable Long commentId,
            @RequestParam Long userId) {

        boolean liked = commentService.toggleLike(commentId, userId);
        int likeCount = commentService.getLikeCount(commentId);

        Map<String,Object> res = new HashMap<>();
        res.put("liked", liked);
        res.put("likeCount", likeCount);
        return ResponseEntity.ok(res);
    }
}
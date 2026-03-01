package com.sunny.musicplayer.musicplayer.repository;

import com.sunny.musicplayer.musicplayer.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySongIdAndParentIsNullOrderByCreatedAtDesc(Long songId);
    List<Comment> findByParent(Comment comment);
}

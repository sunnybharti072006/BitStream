package com.sunny.musicplayer.musicplayer.repository;

import com.sunny.musicplayer.musicplayer.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findBySongIdAndParentIsNullOrderByCreatedAtDesc(Long songId);

    List<Comment> findByParent(Comment comment);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.likedByUsers WHERE c.song.id = :songId AND c.parent IS NULL ORDER BY c.createdAt DESC")
    List<Comment> findBySongIdWithLikes(@Param("songId") Long songId);
}
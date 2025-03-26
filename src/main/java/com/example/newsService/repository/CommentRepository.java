package com.example.newsService.repository;

import com.example.newsService.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "SELECT id FROM comments WHERE user_id =:userId", nativeQuery = true)
    Set<Long> findCommentsIdByUserId(Long userId);


}

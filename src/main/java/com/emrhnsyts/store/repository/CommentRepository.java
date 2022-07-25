package com.emrhnsyts.store.repository;

import com.emrhnsyts.store.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByAppUserId(Long appUserId);

    List<Comment> findByProductId(Long productId);
}

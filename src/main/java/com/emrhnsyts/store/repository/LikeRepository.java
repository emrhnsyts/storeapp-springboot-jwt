package com.emrhnsyts.store.repository;

import com.emrhnsyts.store.entity.Like;
import com.emrhnsyts.store.response.LikeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByProductId(Long productId);

    List<Like> findByAppUserId(Long appUserId);
}

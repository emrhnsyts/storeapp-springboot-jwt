package com.emrhnsyts.store.repository;

import com.emrhnsyts.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

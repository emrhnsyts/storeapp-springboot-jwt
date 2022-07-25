package com.emrhnsyts.store.repository;

import com.emrhnsyts.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

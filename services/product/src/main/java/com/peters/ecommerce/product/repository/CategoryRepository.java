package com.peters.ecommerce.product.repository;

import com.peters.ecommerce.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CategoryData;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryData, Integer> {
	public CategoryData findByCategoryId(int id);
}

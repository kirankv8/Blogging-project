package com.application.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

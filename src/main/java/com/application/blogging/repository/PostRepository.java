package com.application.blogging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.Category;
import com.application.blogging.model.Post;
import com.application.blogging.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
}

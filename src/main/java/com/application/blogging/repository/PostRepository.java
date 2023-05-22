package com.application.blogging.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.Category;
import com.application.blogging.model.Post;
import com.application.blogging.model.Users;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(Users user);

	List<Post> findByCategory(Category category);

	Optional<List<Post>> findByTitleContaining(String title) ;
}

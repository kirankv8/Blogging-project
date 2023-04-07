package com.application.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.Comments;


public interface CommentRepository  extends JpaRepository<Comments, Long>{

}

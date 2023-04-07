package com.application.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}

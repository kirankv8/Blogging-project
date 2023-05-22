package com.application.blogging.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogging.model.Users;

public interface UserRepository  extends JpaRepository<Users, Long>{

	Optional<Users> findByEmail(String email);

}

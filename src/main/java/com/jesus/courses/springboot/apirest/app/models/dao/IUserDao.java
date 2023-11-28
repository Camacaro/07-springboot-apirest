package com.jesus.courses.springboot.apirest.app.models.dao;

import com.jesus.courses.springboot.apirest.app.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}


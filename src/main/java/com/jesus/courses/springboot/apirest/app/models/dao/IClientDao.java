package com.jesus.courses.springboot.apirest.app.models.dao;

import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDao extends JpaRepository<Client, Long> {

}

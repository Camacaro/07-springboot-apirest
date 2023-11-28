package com.jesus.courses.springboot.apirest.app.models.services;

import com.jesus.courses.springboot.apirest.app.models.entity.Client;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();

    public Client findById(Long id);

    public Client save(Client client);

    public void delete(Long id);
}

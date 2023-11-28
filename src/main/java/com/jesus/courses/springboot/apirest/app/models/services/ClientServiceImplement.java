package com.jesus.courses.springboot.apirest.app.models.services;

import com.jesus.courses.springboot.apirest.app.models.dao.IClientDao;
import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImplement implements IClientService{

    private final IClientDao clientDao;

    @Autowired
    public ClientServiceImplement(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientDao.findAll();
    }
}

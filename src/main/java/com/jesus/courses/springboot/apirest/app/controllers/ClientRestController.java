package com.jesus.courses.springboot.apirest.app.controllers;

import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import com.jesus.courses.springboot.apirest.app.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private final IClientService clientService;

    @Autowired
    public ClientRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Client> index() {
        return clientService.findAll();
    }

}

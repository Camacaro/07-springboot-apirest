package com.jesus.courses.springboot.apirest.app.controllers;

import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import com.jesus.courses.springboot.apirest.app.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Client show(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        Client currectClient = clientService.findById(id);

        currectClient.setEmail(client.getEmail());
        currectClient.setName(client.getName());
        currectClient.setLastname(client.getLastname());

        return clientService.save(currectClient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

}

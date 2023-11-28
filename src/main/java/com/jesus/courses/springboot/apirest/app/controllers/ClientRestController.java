package com.jesus.courses.springboot.apirest.app.controllers;

import com.jesus.courses.springboot.apirest.app.models.dao.RequestResponse;
import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import com.jesus.courses.springboot.apirest.app.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PreAuthorize("hasRole('ADMIN')")
    // ?: cualquier tipo de objeto
    public ResponseEntity<?> show(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Client client = null;
        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta");
            String msgErro = e.getMessage();
            String specificError = e.getMostSpecificCause().getMessage();
            response.put("error", msgErro + ": " + specificError);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client == null) {
            response.put("message", "El cliente ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestResponse> save(@RequestBody Client client) {
        RequestResponse response = new RequestResponse();
        Client newClient = null;
        try {
            newClient = clientService.save(client);

            response.setPayload("client", newClient);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("cliente creado con éxito");

        } catch (DataAccessException e) {
            String msgErro = e.getMessage();
            String specificError = e.getMostSpecificCause().getMessage();
            String msg = msgErro + ": " + specificError;

            response.setMessage(msg);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response.respond();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestResponse> update(@PathVariable Long id, @RequestBody Client client) {
        RequestResponse response = new RequestResponse();
        Client currectClient = null;

        try {
            currectClient = clientService.findById(id);

            if (currectClient == null) {
                response.setMessage("cliente no se existe en la db");
                response.setStatus(HttpStatus.BAD_REQUEST);
            } else {
                currectClient.setEmail(client.getEmail());
                currectClient.setName(client.getName());
                currectClient.setLastname(client.getLastname());

                response.setMessage("Cliente actualizado");
                response.setStatus(HttpStatus.CREATED);
                response.setPayload("client", currectClient);
            }
        } catch (DataAccessException e) {
            String msgErro = e.getMessage();
            String specificError = e.getMostSpecificCause().getMessage();
            String msg = msgErro + ": " + specificError;

            response.setMessage(msg);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response.respond();
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long id) {
//        clientService.delete(id);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RequestResponse> delete(@PathVariable Long id) {
        RequestResponse response = new RequestResponse();
        try {
            clientService.delete(id);
            response.setMessage("Cliente eliminado");
            response.setStatus(HttpStatus.OK);
        } catch (DataAccessException e) {
            String msgErro = e.getMessage();
            String specificError = e.getMostSpecificCause().getMessage();
            String msg = msgErro + ": " + specificError;

            response.setMessage(msg);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response.respond();
    }

}

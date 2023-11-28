package com.jesus.courses.springboot.apirest.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

/*
    Clase entity de persistencia que está conectada a una base de datos
 */

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "no puede estar vacío")
    private String lastname;

    @Email(message = "no es una dirección de correo válida")
    @NotEmpty(message = "no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Date createAt) {
        this.createdAt = createAt;
    }
}

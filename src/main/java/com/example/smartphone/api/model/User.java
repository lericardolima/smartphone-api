package com.example.smartphone.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representação do usuário do sistema.
 *
 * @author Ricardo Rocha
 * @version 1.1.0
 */
@Entity
@Table(name = "smartphone_user")
public class User {

    /**
     * Chave primária do recurso definido pelo usuário.
     */
    @Id
    private String username;

    /**
     * Nome do usuário.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Sobrenome do usuário.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Email do usuário.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Número de telefone do usuário.
     */
    @Column(unique = true)
    private String phoneNumber;

    /**
     * Senha do usuário do sistema.
     */
    @Column(nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

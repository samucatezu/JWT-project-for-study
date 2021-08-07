package com.github.samucatezu.java.testprojectwebjava.dto;

import com.github.samucatezu.java.testprojectwebjava.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)

public class UserResponseDTO {
    private Long id;

    private String nome;
    private String email;
    private String senha;

    public UserResponseDTO(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public UserResponseDTO(){}

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user.getNome(), user.getEmail(), user.getSenha());
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


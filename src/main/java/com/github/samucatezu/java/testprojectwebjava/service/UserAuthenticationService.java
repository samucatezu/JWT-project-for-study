package com.github.samucatezu.java.testprojectwebjava.service;

import com.github.samucatezu.java.testprojectwebjava.dto.DadosLogin;
import com.github.samucatezu.java.testprojectwebjava.exception.ExistingEmailException;
import com.github.samucatezu.java.testprojectwebjava.exception.ExpiredTokenException;
import com.github.samucatezu.java.testprojectwebjava.exception.InvalidLoginException;
import com.github.samucatezu.java.testprojectwebjava.exception.InvalidTokenException;
import com.github.samucatezu.java.testprojectwebjava.model.User;
import com.github.samucatezu.java.testprojectwebjava.repository.UserRepository;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    public User authenticate(DadosLogin dados, String token){
        User user = userRepository.findByEmail(dados.getEmail()).orElseThrow(ExistingEmailException::new);
        if(dados.getSenha().equals(user.getSenha()) && !token.isEmpty() && validate(token)) {
            return user;
        }
        else {
            throw new InvalidLoginException();
        }
    }

    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);

            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            //Verifica se o token está expirado
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpiredTokenException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (ExpiredTokenException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }


}



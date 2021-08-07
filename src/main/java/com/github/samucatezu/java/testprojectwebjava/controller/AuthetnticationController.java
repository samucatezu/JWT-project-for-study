package com.github.samucatezu.java.testprojectwebjava.controller;

import com.github.samucatezu.java.testprojectwebjava.dto.DadosLogin;
import com.github.samucatezu.java.testprojectwebjava.dto.UserAutheticatedDTO;
import com.github.samucatezu.java.testprojectwebjava.model.User
import com.github.samucatezu.java.testprojectwebjava.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthetnticationController {
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthetnticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }

    public AuthetnticationController(){

    }


    @PostMapping("/login")
    public ResponseEntity<UserAutheticatedDTO> autenticar(@RequestBody DadosLogin dadosLogin, @RequestHeader String Authorization){
        User user = userAuthenticationService.authenticate(dadosLogin, Authorization);
        return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
    }
}

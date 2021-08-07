package com.github.samucatezu.java.testprojectwebjava.controller;

import com.github.samucatezu.java.testprojectwebjava.dto.UserAutheticatedDTO;
import com.github.samucatezu.java.testprojectwebjava.dto.UserRegistrationDTO;
import com.github.samucatezu.java.testprojectwebjava.model.User
import com.github.samucatezu.java.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    public UserRegistrationController(){

    }

    @PostMapping("/user")
    public ResponseEntity<UserAutheticatedDTO> registrate(@RequestBody UserRegistrationDTO userRegistrationDTO){
        User user = userRegistrationService.registrate(userRegistrationDTO.toUser());
        return  new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
    }
}

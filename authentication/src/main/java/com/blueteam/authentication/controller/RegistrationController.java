package com.blueteam.authentication.controller;

import com.blueteam.authentication.dto.UserDTO;
import com.blueteam.authentication.entity.User;
import com.blueteam.authentication.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.InstanceAlreadyExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> registerUserAccount(
            @Valid @RequestBody UserDTO userDto) throws InstanceAlreadyExistsException {
            String response = "Registered successfully, please activate your account";

        User registered = userService.registerNewUserAccount(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestBody String  code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }


}



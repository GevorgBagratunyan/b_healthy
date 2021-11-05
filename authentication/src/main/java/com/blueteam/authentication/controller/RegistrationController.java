package com.blueteam.authentication.controller;

import com.blueteam.authentication.dto.UserDTO;
import com.blueteam.authentication.entity.User;
import com.blueteam.authentication.service.UserService;
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
    public ResponseEntity<User> registerUserAccount(
            @Valid @RequestBody UserDTO userDto) throws InstanceAlreadyExistsException {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("register").toUriString());

            User registered = userService.registerNewUserAccount(userDto);
            return new ResponseEntity<>(registered, HttpStatus.OK);
    }
}

package com.patika.auth_service.controller;

import com.patika.auth_service.dto.request.UserLoginRequest;
import com.patika.auth_service.dto.request.UserSaveRequest;
import com.patika.auth_service.dto.response.GenericResponse;
import com.patika.auth_service.model.User;
import com.patika.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<User> register(@RequestBody UserSaveRequest request) {
        return authService.register(request);
    }

    @PostMapping(path = "/login")
    public GenericResponse<String> login(@RequestBody UserLoginRequest request) {
        return authService.login(request);
    }

}

package com.patika.auth_service.service;


import com.patika.auth_service.client.service.UserClientService;
import com.patika.auth_service.config.PasswordEncoderConfig;
import com.patika.auth_service.converter.UserConverter;
import com.patika.auth_service.dto.request.UserLoginRequest;
import com.patika.auth_service.dto.request.UserSaveRequest;
import com.patika.auth_service.dto.response.GenericResponse;
import com.patika.auth_service.exception.AuthServiceException;
import com.patika.auth_service.exception.ExceptionMessages;
import com.patika.auth_service.model.Role;
import com.patika.auth_service.model.User;
import com.patika.auth_service.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private UserClientService userClientService;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final JwtUtil jwtUtil;

    public AuthService(UserClientService userClientService, PasswordEncoderConfig passwordEncoderConfig, JwtUtil jwtUtil) {
        this.userClientService = userClientService;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.jwtUtil = jwtUtil;
    }

    public GenericResponse<User> register(UserSaveRequest request) {

        boolean isExists = userClientService.existsByEmail(request.getEmail());

        if (isExists) {
            throw new AuthServiceException(ExceptionMessages.USER_ALREADY_DEFINED);
        }

        /*Role adminRole = roleRepository.findAll()
                .stream()
                .filter(role -> role.getName().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new AuthServiceException(ExceptionMessages.ROLE_NOT_FOUND));*/

        //Role role = userClientService.getUsersRoles(request.getEmail())

        Role defaultRole = new Role(2l,"USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(defaultRole);
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoderConfig.bCryptPasswordEncoder().encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .userType(request.getUserType())
                .roleSet(roleSet)
                .build();



        userClientService.save(UserConverter.userToCustomUser(user));

        return GenericResponse.success(user, HttpStatus.CREATED);

    }

    public GenericResponse<String> login(UserLoginRequest request) {

        Optional<User> user = userClientService.getUserByEmail(request.getEmail());

        if (user.isEmpty()){
            throw new AuthServiceException(ExceptionMessages.USER_NOT_FOUND);
        }
        return GenericResponse.success(jwtUtil.generateToken(user.get()),HttpStatus.ACCEPTED);

    }




}

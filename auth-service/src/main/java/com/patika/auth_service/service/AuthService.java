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
import com.patika.auth_service.producer.RabbitProducer;
import com.patika.auth_service.producer.model.Notification;
import com.patika.auth_service.producer.model.enums.NotificationType;
import com.patika.auth_service.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private UserClientService userClientService;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final JwtUtil jwtUtil;
    private final RabbitProducer rabbitProducer;

    public AuthService(UserClientService userClientService, PasswordEncoderConfig passwordEncoderConfig, JwtUtil jwtUtil, RabbitProducer rabbitProducer) {
        this.userClientService = userClientService;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.jwtUtil = jwtUtil;
        this.rabbitProducer = rabbitProducer;
    }

    public GenericResponse<User> register(UserSaveRequest request) {

        boolean isExists = userClientService.existsByEmail(request.getEmail());

        if (isExists) {
            throw new AuthServiceException(ExceptionMessages.USER_ALREADY_DEFINED);
        }

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

        Notification notification = new Notification();
        notification.setNotificationMessage(request.getEmail() + " Kayıt oldu.");
        notification.setNotificationTime(LocalDateTime.now());
        notification.setNotificationUser(request.getEmail());
        notification.setNotificationType(NotificationType.EMAIL);
        rabbitProducer.sendNotification(notification);

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

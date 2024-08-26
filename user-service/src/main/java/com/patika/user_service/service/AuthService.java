package com.patika.user_service.service;

import com.patika.user_service.config.BeanConfig;
import com.patika.user_service.dto.request.UserLoginRequest;
import com.patika.user_service.dto.request.UserSaveRequest;
import com.patika.user_service.exception.UserServiceException;
import com.patika.user_service.exception.ExceptionMessages;
import com.patika.user_service.model.Role;
import com.patika.user_service.model.User;
import com.patika.user_service.model.enums.UserType;
import com.patika.user_service.repository.RoleRepository;
import com.patika.user_service.repository.UserRepository;
import com.patika.user_service.utils.JwtUtil;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
private final BeanConfig beanConfig;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    public AuthService(BeanConfig beanConfig, UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil) {

        this.beanConfig = beanConfig;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }


    public void register(UserSaveRequest request) {

        boolean isExists = userRepository.existsByEmail(request.getEmail());

        if (isExists) {
            throw new UserServiceException(ExceptionMessages.USER_ALREADY_DEFINED);
        }


        Role adminRole = roleRepository.findAll()
                .stream()
                .filter(role -> role.getName().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new UserServiceException(ExceptionMessages.ROLE_NOT_FOUND));


        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(beanConfig.bCryptPasswordEncoder().encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .userType(UserType.INDIVIDUAL)
                .roleSet(Set.of(adminRole))
                .build();

        userRepository.save(user);
    }

    public String login(UserLoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()){
            throw new UserServiceException(ExceptionMessages.USER_NOT_FOUND);
        }
            return jwtUtil.generateToken(user.get());
    }
}

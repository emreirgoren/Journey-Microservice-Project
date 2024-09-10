package com.patika.user_service.service;

import com.patika.user_service.converter.UserConverter;
import com.patika.user_service.dto.response.ChangeRoleResponse;
import com.patika.user_service.exception.ExceptionMessages;
import com.patika.user_service.exception.UserServiceException;
import com.patika.user_service.model.Role;
import com.patika.user_service.model.User;
import com.patika.user_service.repository.RoleRepository;
import com.patika.user_service.repository.UserRepository;
import com.patika.user_service.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    public long getUserId(String email) {
        return userRepository.findByEmail(email).get().getId();
    }

    public boolean existsByEmail(String email) {
        if(userRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }

    public void saveUser(User user) {

        try {
            Optional<Role> role = roleRepository.findById(2l);
            user.setRoleSet(Set.of(role.get()));
            userRepository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public ChangeRoleResponse changeRole(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new UserServiceException(ExceptionMessages.USER_NOT_FOUND);
        }
        User user = optionalUser.get();

        user.getRoleSet().add(roleRepository.findById(1L).get());
        return UserConverter.userToChangeRoleResponse(user);

        //return user.getRoleSet().contains(roleRepository.findById(1L).get());

    }

    public User userInfomation(String token) {
        String email = jwtUtil.extractEmail2(token);
        return userRepository.findByEmail(email).get();
    }
}

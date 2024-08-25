package com.patika.user_service.service;

import com.patika.user_service.model.Role;
import com.patika.user_service.model.User;
import com.patika.user_service.repository.RoleRepository;
import com.patika.user_service.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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



}

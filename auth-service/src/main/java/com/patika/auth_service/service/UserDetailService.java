package com.patika.auth_service.service;

import com.patika.auth_service.client.service.UserClientService;
import com.patika.auth_service.exception.AuthServiceException;
import com.patika.auth_service.exception.ExceptionMessages;
import com.patika.auth_service.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserClientService userClientService;

    public UserDetailService(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userClientService.getUserByEmail(email).orElseThrow(() -> new AuthServiceException(ExceptionMessages.USER_NOT_FOUND));
        return user;
    }
}

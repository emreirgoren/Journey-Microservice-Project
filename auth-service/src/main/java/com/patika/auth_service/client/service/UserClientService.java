package com.patika.auth_service.client.service;

import com.patika.auth_service.client.UserClient;
import com.patika.auth_service.exception.AuthServiceException;
import com.patika.auth_service.exception.ExceptionMessages;
import com.patika.auth_service.model.CustomUser;
import com.patika.auth_service.model.User;
import feign.FeignException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserClientService {

    private final UserClient userClient;

    public UserClientService(UserClient userClient) {
        this.userClient = userClient;
    }


    public boolean existsByEmail(String email) {
        try {
            return userClient.existsByEmail(email);
        }catch (FeignException e){
            throw new AuthServiceException(ExceptionMessages.USER_SERVICE_NOT_CONNECT);
        }
    }

    public void save(CustomUser customUser) {
         try {
             userClient.saveUser(customUser);
         }catch (FeignException e){
             throw new AuthServiceException(ExceptionMessages.USER_SERVICE_NOT_CONNECT);
         }
    }

    public Optional<User> getUserByEmail(String email) {
        try {
            return userClient.getUserByEmail(email);
        }catch (FeignException e){
            throw new AuthServiceException(ExceptionMessages.USER_SERVICE_NOT_CONNECT);
        }
    }

}

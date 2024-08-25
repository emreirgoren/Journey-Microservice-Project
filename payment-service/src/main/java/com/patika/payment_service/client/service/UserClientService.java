package com.patika.payment_service.client.service;

import com.patika.payment_service.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public class UserClientService {

    private final UserClient userClient;

    public UserClientService(UserClient userClient) {
        this.userClient = userClient;
    }

    public long getUserIdByEmail(String userEmail) {
        return userClient.getUserIdByEmail(userEmail);
    }
}

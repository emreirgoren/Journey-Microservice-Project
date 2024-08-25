package com.patika.auth_service.client;

import com.patika.auth_service.model.CustomUser;
import com.patika.auth_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(value = "user-service",url = "localhost:9001/api/v1/users")
public interface UserClient {


    @GetMapping("/isExists/{email}")
    boolean existsByEmail(@PathVariable String email);

    @PostMapping("/save")
    void saveUser(@RequestBody CustomUser customUser);

    @GetMapping("/getUser/{email}")
    Optional<User> getUserByEmail(@PathVariable String email);
}

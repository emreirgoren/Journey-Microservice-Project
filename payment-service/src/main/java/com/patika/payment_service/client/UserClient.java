package com.patika.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user-service", url = "localhost:9001/api/v1/users")
public interface UserClient {

    @GetMapping("/{email}")
    long getUserIdByEmail(@PathVariable String email);
}

package com.patika.user_service.controller;

import com.patika.user_service.dto.response.ChangeRoleResponse;
import com.patika.user_service.model.User;
import com.patika.user_service.service.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public long getUserIdByUserEmail(@PathVariable String email){
        return userService.getUserId(email);
    }


    @GetMapping("/isExists/{email}")
    public boolean existsByEmail(@PathVariable String email){
        boolean isExists = userService.existsByEmail(email);
        return isExists;
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/getUser/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping(path = "/changeRole/{email}")
    public ChangeRoleResponse changeRole(@PathVariable String email){

        return userService.changeRole(email);
    }

    @PostMapping(path = "/userInformation")
    public User userInformation(@RequestHeader("Authorization") String token){
        return userService.userInfomation(token);
    }

}

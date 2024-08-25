package com.patika.auth_service.converter;

import com.patika.auth_service.model.CustomUser;
import com.patika.auth_service.model.Role;
import com.patika.auth_service.model.User;
import com.patika.auth_service.model.enums.Gender;
import com.patika.auth_service.model.enums.UserType;

import java.time.LocalDate;
import java.util.Set;

public class UserConverter {

    public static CustomUser userToCustomUser(User user){
        CustomUser customUser = new CustomUser();

        customUser.setId(user.getId());
        customUser.setFirstName(user.getFirstName());
        customUser.setLastName(user.getLastName());
        customUser.setEmail(user.getEmail());
        customUser.setPassword(user.getPassword());
        customUser.setBirthDate(user.getBirthDate());
        customUser.setPhoneNumber(user.getPhoneNumber());
        customUser.setGender(user.getGender());
        customUser.setUserType(user.getUserType());
        customUser.getRoleSet().add(user.getRoleSet().stream().findAny().get());

        return customUser;
    }

}

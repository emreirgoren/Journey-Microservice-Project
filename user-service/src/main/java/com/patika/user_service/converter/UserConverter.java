package com.patika.user_service.converter;

import com.patika.user_service.dto.response.ChangeRoleResponse;
import com.patika.user_service.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static ChangeRoleResponse userToChangeRoleResponse(User user){
        ChangeRoleResponse changeRoleResponse = new ChangeRoleResponse();
        changeRoleResponse.setFirstName(user.getFirstName());
        changeRoleResponse.setLastName(user.getLastName());
        changeRoleResponse.setEmail(user.getEmail());
        changeRoleResponse.setRoleSet(user.getRoleSet());
        return changeRoleResponse;
    }

}

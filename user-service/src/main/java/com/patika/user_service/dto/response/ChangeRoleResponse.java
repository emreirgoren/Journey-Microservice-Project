package com.patika.user_service.dto.response;

import com.patika.user_service.model.Role;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChangeRoleResponse {

    private String firstName;

    private String lastName;

    private String email;

    private Set<Role> roleSet;


}

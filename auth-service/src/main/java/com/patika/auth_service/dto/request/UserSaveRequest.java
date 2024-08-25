package com.patika.auth_service.dto.request;


import com.patika.auth_service.model.Role;
import com.patika.auth_service.model.enums.Gender;
import com.patika.auth_service.model.enums.UserType;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSaveRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String phoneNumber;

    private Gender gender;

    private UserType userType;

    //private Set<Role> roleSet;

}

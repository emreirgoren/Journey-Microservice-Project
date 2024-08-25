package com.patika.auth_service.model;

import com.patika.auth_service.model.enums.Gender;
import com.patika.auth_service.model.enums.UserType;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String phoneNumber;

    private Gender gender;

    private UserType userType;

    private Set<Role> roleSet = new HashSet<>();

}

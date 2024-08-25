package com.patika.auth_service.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {


    public static final String USER_ALREADY_DEFINED = "User already defined.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_SERVICE_NOT_CONNECT = "User service could not be connected.";
    public static final String ROLE_NOT_FOUND = "Role not found.";
}

package com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericResponseFailedConstants {

    public static final String FAILED = "Failed.";
    public static final String LIMIT_EXCEEDED_JOURNEY = "Limit exceeded for this route and vehicle type on the given date.";
    public static final String JOURNEY_NOT_FOUND = "Journey not found.";
    public static final String ALREADY_CANCELLED ="Journey already cancelled." ;
}

package com.patika.journey_service.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String exceptionMessage;
    private String exceptionStatus;
    private HttpStatus exceptionStatusCode;

}

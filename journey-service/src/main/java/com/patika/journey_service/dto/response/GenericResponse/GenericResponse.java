package com.patika.journey_service.dto.response.GenericResponse;


import com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants.GenericResponseFailedConstants;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants.GenericResponseSuccessConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {

    private String message;
    private String status;
    private HttpStatus httpStatus;
    private T data;

    public static <T> GenericResponse<T> success(String message,
                                                 HttpStatus httpStatus,
                                                 T data){
        return GenericResponse.<T>builder()
                .message(message)
                .status(GenericResponseSuccessConstants.SUCCESS)
                .httpStatus(httpStatus)
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> failed(String message,
                                                HttpStatus httpStatus,
                                                T data){
        return GenericResponse.<T>builder()
                .message(message)
                .status(GenericResponseFailedConstants.FAILED)
                .httpStatus(httpStatus)
                .data(data)
                .build();
    }



}

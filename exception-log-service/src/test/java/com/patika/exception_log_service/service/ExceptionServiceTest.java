package com.patika.exception_log_service.service;

import com.patika.exception_log_service.consumer.ExceptionTemplate;
import com.patika.exception_log_service.model.Exception;
import com.patika.exception_log_service.repository.ExceptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ExceptionServiceTest {

    @InjectMocks
    private ExceptionService exceptionService;

    @Mock
    private ExceptionRepository exceptionRepository;


    @Test
    void handleException(){

        //given
        Mockito.when(exceptionRepository.save(Mockito.any(Exception.class))).thenReturn(prepareException());

        //when
        exceptionService.handleException(prepareExceptionTemplate());

        //then
        Mockito.verify(exceptionRepository,Mockito.times(1)).save(Mockito.any(Exception.class));

    }

    private Exception prepareException() {
        Exception exception =Exception.builder()
                        .exceptionMessage("Test Message")
                                .exceptionStatusCode(HttpStatus.NOT_FOUND)
                                        .serviceType("test-service").build();
        return exception;
    }

    private ExceptionTemplate prepareExceptionTemplate() {

        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setExceptionMessage("Test Message");
        exceptionTemplate.setExceptionStatusCode(HttpStatus.NOT_FOUND);
        exceptionTemplate.setServiceType("test-service");
        return exceptionTemplate;
    }

}

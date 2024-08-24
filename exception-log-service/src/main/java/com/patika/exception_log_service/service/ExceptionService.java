package com.patika.exception_log_service.service;

import com.patika.exception_log_service.consumer.ExceptionTemplate;
import com.patika.exception_log_service.model.Exception;
import com.patika.exception_log_service.repository.ExceptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {

    private ExceptionRepository exceptionRepository;

    public ExceptionService(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    public void handleException(ExceptionTemplate exceptionTemplate) {

        Exception exception = Exception.builder()
                        .serviceType(exceptionTemplate.getServiceType())
                                .exceptionMessage(exceptionTemplate.getExceptionMessage())
                                        .exceptionStatusCode(exceptionTemplate.getExceptionStatusCode())
                                                .build();
        exceptionRepository.save(exception);

    }
}

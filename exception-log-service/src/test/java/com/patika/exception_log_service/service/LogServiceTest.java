package com.patika.exception_log_service.service;

import com.patika.exception_log_service.consumer.LogTemplate;
import com.patika.exception_log_service.model.Log;
import com.patika.exception_log_service.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Mock
    private LogRepository logRepository;

    @Test
    void handleLog(){
        //given
        LogTemplate logTemplate = prepareLogTemplate();

        //when
        logService.handleLog(logTemplate);

        //then
        Mockito.verify(logRepository,Mockito.times(1)).save(Mockito.any(Log.class));



    }

    private LogTemplate prepareLogTemplate() {
        LogTemplate logTemplate = new LogTemplate();
        logTemplate.setLog("log");
        logTemplate.setServiceType("test-service");
        logTemplate.setMessage("test-message");
        return logTemplate;
    }

}

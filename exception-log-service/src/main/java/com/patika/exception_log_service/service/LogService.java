package com.patika.exception_log_service.service;

import com.patika.exception_log_service.consumer.LogTemplate;
import com.patika.exception_log_service.model.Log;
import com.patika.exception_log_service.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void handleLog(LogTemplate logTemplate) {
        Log log = new Log();
        log.setLog(logTemplate.getLog());
        log.setServiceType(logTemplate.getServiceType());
        log.setMessage(logTemplate.getMessage());
        logRepository.save(log);
    }
}

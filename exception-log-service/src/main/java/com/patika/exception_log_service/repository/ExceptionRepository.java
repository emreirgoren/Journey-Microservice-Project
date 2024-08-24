package com.patika.exception_log_service.repository;

import com.patika.exception_log_service.model.Exception;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExceptionRepository extends MongoRepository<Exception,String> {
}

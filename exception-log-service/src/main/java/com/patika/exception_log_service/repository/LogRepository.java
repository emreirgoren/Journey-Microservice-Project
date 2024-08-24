package com.patika.exception_log_service.repository;

import com.patika.exception_log_service.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log,String> {
}

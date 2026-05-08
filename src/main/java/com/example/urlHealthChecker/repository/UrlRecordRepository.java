package com.example.urlHealthChecker.repository;

import com.example.urlHealthChecker.model.UrlRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRecordRepository extends CrudRepository<UrlRecord, String> {}
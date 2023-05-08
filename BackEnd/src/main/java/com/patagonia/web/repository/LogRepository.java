package com.patagonia.web.repository;

import com.patagonia.web.entity.LogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogModel, String>, JpaSpecificationExecutor<LogModel> {
}

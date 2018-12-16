package com.credit.tim.logprocessor.Dao;

import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<LogStatus, String> {

}

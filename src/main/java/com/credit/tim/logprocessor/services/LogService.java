package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.Dao.LogDao;
import com.credit.tim.logprocessor.domain.LogStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class LogService {

    @Autowired
    private LogDao logDao;


    public void save(LogStatus task) {
        logDao.save(task);
    }

    public void save(List<LogStatus> tasks) {
        tasks.forEach(log -> logDao.save(log));
    }
}

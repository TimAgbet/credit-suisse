package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.Dao.LogDao;
import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.credit.tim.logprocessor.services.TaskBuilder.TaskBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Mock
    private LogDao logDao;

    @Test
    public void shouldSaveLog() {

        //Given
        Task task = TaskBuilder()
                .setId("test1")
                .setHost("testHost")
                .setState("STARTED")
                .build();
        //When
        LogStatus logStatus = TaskHelper.convertTo(task);
        logService.save(logStatus);

        //Then
        verify(logDao).save(logStatus);

    }

    @Test
    public void shouldSaveMultipleLogs() {

        //Given
        List<LogStatus> logStatuses = new ArrayList<>();

        Task firstTask = TaskBuilder()
                .setId("test1")
                .setHost("testHost")
                .setState("STARTED")
                .build();
        LogStatus expected = TaskHelper.convertTo(firstTask);

        //When
        logStatuses.add(expected);
        logService.save(logStatuses);

        //Then
        verify(logDao).save(expected);

    }
}
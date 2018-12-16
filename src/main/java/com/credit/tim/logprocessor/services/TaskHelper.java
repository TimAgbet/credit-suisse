package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.domain.Task;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskHelper {

    public static final String FINISHED = "FINISHED";
    public static Predicate<Task> endState = p -> FINISHED.equals(p.getState());

    public static Long calculateDuration(Long startTime, Long endTime) {
        Timestamp firstTime = new Timestamp(startTime);
        Timestamp secondTime = new Timestamp(endTime);
        return (secondTime.getTime() - firstTime.getTime());
    }

    public static LogStatus convertTo(Task task) {
        LogStatus logStatus = new LogStatus();
        logStatus.setEventId(task.getId());
        logStatus.setHost(task.getHost());
        logStatus.setType(task.getType());
        return logStatus;
    }

    public static LogStatus validDuration(Task endTask, Task task, Long duration) {
        LogStatus log = new LogStatus();
        if (duration > 4) {
            log.setEventId(endTask.getId());
            log.setType(task.getType());
            log.setHost(task.getHost());
            log.setDuration(duration);
            log.setAlert(true);
            return log;
        }
        return null;
    }

    public static List<Task> getFinishedLogs(List<Task> tasks) {
        return tasks.stream().filter(endState)
                .collect(Collectors
                        .toList());
    }
}

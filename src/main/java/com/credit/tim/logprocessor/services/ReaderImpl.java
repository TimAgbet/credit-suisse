package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.domain.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.credit.tim.logprocessor.services.TaskHelper.*;

@Component
public class ReaderImpl implements Reader {

    @Autowired
    private LogService logService;
    private Logger LOGGER = LoggerFactory.getLogger(ReaderImpl.class);
    private File file;

    public ReaderImpl(File file) {
        this.file = file;
    }

    @Autowired
    public ReaderImpl(LogService logService) {
        this.logService = logService;
    }

    public void processStream() {
        LOGGER.debug("The file name is: " + file.getAbsolutePath());
        try {

            List<Task> tasks = extractTask(file);
            List<Task> finishedLogs = getFinishedLogs(tasks);
            List<LogStatus> matchLogStatuses = matchLogs(tasks, finishedLogs);

            if (!matchLogStatuses.isEmpty()) {
                new Thread(() -> logService.save(matchLogStatuses)) {{
                    start();
                }}.join();
            }
        } catch (Exception ex) {
            LOGGER.error("There was an unexpected error processing the entered file: " + ex);
        }
    }

    private List<Task> extractTask(File file) throws IOException {
        List<Task> tasks = new ArrayList<>();
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        Gson gson = new GsonBuilder().create();

        reader.beginArray();
        while (reader.hasNext()) {
            Task task = gson.fromJson(reader, Task.class);
            LOGGER.debug(task.toString());
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    private List<LogStatus> matchLogs(List<Task> allTasks, List<Task> endTasks) {
        //TODO:To be efficient, you can loop though the logs with end dates as there will be less of them
        List<LogStatus> matched = new ArrayList<>();

        for (Task endTask : endTasks) {
            Optional<Task> task = allTasks.stream().filter(p -> p.getId().equals(endTask.getId())).findFirst();
            if (task.isPresent()) {
                Task presentTask = task.get();
                Long duration = calculateDuration(presentTask.getTimestamp(), endTask.getTimestamp());
                LogStatus status = validDuration(endTask, presentTask, duration);
                if (status != null) {
                    matched.add(status);
                }
            }
        }
        return matched;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.domain.Task;

public class TaskBuilder {

    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;

    public static TaskBuilder TaskBuilder() {
        return new TaskBuilder();
    }

    public TaskBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public TaskBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public TaskBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public TaskBuilder setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;

    }

    public Task build(){
        Task task = new Task();
        task.setId(id);
        task.setHost(host);
        task.setState(state);
        task.setTimestamp(timestamp);
        task.setType(type);
        return  task;
    }

}

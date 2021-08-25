package com.company.Objects;

public class Task {

    private int id;
    private String task;
    private int priority;
    private String category;
    private String status;
    private String deadline;
    private int userId;

    public Task() {}

    public Task(int id, String task, int priority, String category, String status, String deadline, int userId) {
        this.id = id;
        this.task = task;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.deadline = deadline;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

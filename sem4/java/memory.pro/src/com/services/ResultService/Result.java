package com.services.ResultService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Result {

    private int id;
    private String username;

    private int grade;
    private int dataCount;

    private LocalDateTime startTime;
    private int memorizeTime;
    private int rememberTime;

    private int minMemorizeTime;
    private int maxMemorizeTime;
    private int avgMemorizeTime;

    private int correctAns;

    private ArrayList<ResultData> data;

    public int getErrors() {
        return dataCount - correctAns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getMemorizeTime() {
        return memorizeTime;
    }

    public void setMemorizeTime(int memorizeTime) {
        this.memorizeTime = memorizeTime;
    }

    public int getRememberTime() {
        return rememberTime;
    }

    public void setRememberTime(int rememberTime) {
        this.rememberTime = rememberTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return id == result.id &&
                memorizeTime == result.memorizeTime &&
                rememberTime == result.rememberTime &&
                grade == result.grade &&
                Objects.equals(username, result.username) &&
                Objects.equals(startTime, result.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, startTime, memorizeTime, rememberTime, grade);
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public int getAvgMemorizeTime() {
        return avgMemorizeTime;
    }

    public void setAvgMemorizeTime(int avgMemorizeTime) {
        this.avgMemorizeTime = avgMemorizeTime;
    }

    public int getMinMemorizeTime() {
        return minMemorizeTime;
    }

    public void setMinMemorizeTime(int minMemorizeTime) {
        this.minMemorizeTime = minMemorizeTime;
    }

    public int getMaxMemorizeTime() {
        return maxMemorizeTime;
    }

    public void setMaxMemorizeTime(int maxMemorizeTime) {
        this.maxMemorizeTime = maxMemorizeTime;
    }

    public ArrayList<ResultData> getData() {
        return data;
    }

    public void setData(ArrayList<ResultData> data) {
        this.data = data;
    }
}

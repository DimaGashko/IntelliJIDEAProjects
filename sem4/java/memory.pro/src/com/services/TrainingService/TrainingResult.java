package com.services.TrainingService;

import java.util.ArrayList;
import java.util.Objects;

public class TrainingResult {
    private ArrayList<Integer> timesToMemorize;
    private ArrayList<String> answers;
    private int timeToRemember;
    private String trainingType;
    private int dataCount;

    public ArrayList<Integer> getTimesToMemorize() {
        return timesToMemorize;
    }

    public void setTimesToMemorize(ArrayList<Integer> timesToMemorize) {
        this.timesToMemorize = timesToMemorize;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getTimeToRemember() {
        return timeToRemember;
    }

    public void setTimeToRemember(int timeToRemember) {
        this.timeToRemember = timeToRemember;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingResult that = (TrainingResult) o;
        return timeToRemember == that.timeToRemember &&
                dataCount == that.dataCount &&
                Objects.equals(timesToMemorize, that.timesToMemorize) &&
                Objects.equals(answers, that.answers) &&
                Objects.equals(trainingType, that.trainingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timesToMemorize, answers, timeToRemember, trainingType, dataCount);
    }

    @Override
    public String toString() {
        return "TrainingResult{" +
                "timesToMemorize=" + timesToMemorize +
                ", answers=" + answers +
                ", timeToRemember=" + timeToRemember +
                ", trainingType='" + trainingType + '\'' +
                ", dataCount=" + dataCount +
                '}';
    }
}

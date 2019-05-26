package com.components.ResultComponent;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultComponent extends Component {

    SimpleIntegerProperty resultId = new SimpleIntegerProperty();
    SimpleStringProperty username = new SimpleStringProperty();
    SimpleIntegerProperty grade = new SimpleIntegerProperty();
    SimpleIntegerProperty dataCount = new SimpleIntegerProperty();
    SimpleStringProperty trainingType = new SimpleStringProperty();
    SimpleStringProperty startTime = new SimpleStringProperty();
    SimpleIntegerProperty memorizeTime = new SimpleIntegerProperty();
    SimpleIntegerProperty rememberTime = new SimpleIntegerProperty();
    SimpleIntegerProperty minMemorizeTime = new SimpleIntegerProperty();
    SimpleIntegerProperty maxMemorizeTime = new SimpleIntegerProperty();
    SimpleIntegerProperty avgMemorizeTime = new SimpleIntegerProperty();
    SimpleIntegerProperty correct = new SimpleIntegerProperty();
    SimpleIntegerProperty errors = new SimpleIntegerProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(String trainingType, int resultId) {
        this.trainingType.set(trainingType);
        this.resultId.set(resultId);

        System.out.println(trainingType);
        System.out.println(resultId);
    }

    public int getResultId() {
        return resultId.get();
    }

    public SimpleIntegerProperty resultIdProperty() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId.set(resultId);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getDataCount() {
        return dataCount.get();
    }

    public SimpleIntegerProperty dataCountProperty() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount.set(dataCount);
    }

    public String getTrainingType() {
        return trainingType.get();
    }

    public SimpleStringProperty trainingTypeProperty() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType.set(trainingType);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public int getMemorizeTime() {
        return memorizeTime.get();
    }

    public SimpleIntegerProperty memorizeTimeProperty() {
        return memorizeTime;
    }

    public void setMemorizeTime(int memorizeTime) {
        this.memorizeTime.set(memorizeTime);
    }

    public int getRememberTime() {
        return rememberTime.get();
    }

    public SimpleIntegerProperty rememberTimeProperty() {
        return rememberTime;
    }

    public void setRememberTime(int rememberTime) {
        this.rememberTime.set(rememberTime);
    }

    public int getMinMemorizeTime() {
        return minMemorizeTime.get();
    }

    public SimpleIntegerProperty minMemorizeTimeProperty() {
        return minMemorizeTime;
    }

    public void setMinMemorizeTime(int minMemorizeTime) {
        this.minMemorizeTime.set(minMemorizeTime);
    }

    public int getMaxMemorizeTime() {
        return maxMemorizeTime.get();
    }

    public SimpleIntegerProperty maxMemorizeTimeProperty() {
        return maxMemorizeTime;
    }

    public void setMaxMemorizeTime(int maxMemorizeTime) {
        this.maxMemorizeTime.set(maxMemorizeTime);
    }

    public int getAvgMemorizeTime() {
        return avgMemorizeTime.get();
    }

    public SimpleIntegerProperty avgMemorizeTimeProperty() {
        return avgMemorizeTime;
    }

    public void setAvgMemorizeTime(int avgMemorizeTime) {
        this.avgMemorizeTime.set(avgMemorizeTime);
    }

    public int getCorrect() {
        return correct.get();
    }

    public SimpleIntegerProperty correctProperty() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct.set(correct);
    }

    public int getErrors() {
        return errors.get();
    }

    public SimpleIntegerProperty errorsProperty() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors.set(errors);
    }

    public int getGrade() {
        return grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }
}

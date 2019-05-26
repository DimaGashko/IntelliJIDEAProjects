package com.components.ResultComponent;

import com.services.ResultService.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lib.Alerts.Alerts;
import lib.Component.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResultComponent extends Component {

    @FXML private TableView fxDataTable;
    @FXML private TableColumn fxDataTableIndex;
    @FXML private TableColumn fxDataTableTime;
    @FXML private TableColumn fxDataTableAnswer;
    @FXML private TableColumn fxDataTableCorrect;

    private SimpleIntegerProperty resultId = new SimpleIntegerProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleIntegerProperty grade = new SimpleIntegerProperty();
    private SimpleIntegerProperty dataCount = new SimpleIntegerProperty();
    private SimpleStringProperty trainingType = new SimpleStringProperty();
    private SimpleStringProperty startTime = new SimpleStringProperty();
    private SimpleIntegerProperty memorizeTime = new SimpleIntegerProperty();
    private SimpleIntegerProperty rememberTime = new SimpleIntegerProperty();
    private SimpleIntegerProperty minMemorizeTime = new SimpleIntegerProperty();
    private SimpleIntegerProperty maxMemorizeTime = new SimpleIntegerProperty();
    private SimpleIntegerProperty avgMemorizeTime = new SimpleIntegerProperty();
    private SimpleIntegerProperty correct = new SimpleIntegerProperty();
    private SimpleIntegerProperty errors = new SimpleIntegerProperty();

    private ResultService resultService;
    private Result result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(String trainingType, int resultId) {
        this.trainingType.set(trainingType);
        this.resultId.set(resultId);

        initResultService();

        Optional<Result> resultOption = resultService.loadResult(resultId);

        if (resultOption.isEmpty()) {
            alerts.show(Alerts.alertErr, "The result not found");
        }

        result = resultOption.get();
        initProperties();

        initDataTable();
    }

    private void initProperties() {
        username.set(result.getUsername());
        grade.set(result.getGrade());
        dataCount.set(result.getDataCount());
        startTime.set(result.getStartTime().toString());
        memorizeTime.set(result.getMemorizeTime());
        rememberTime.set(result.getRememberTime());
        minMemorizeTime.set(result.getMinMemorizeTime());
        maxMemorizeTime.set(result.getMaxMemorizeTime());
        avgMemorizeTime.set(result.getAvgMemorizeTime());
        correct.set(result.getCorrectAns());
        errors.set(result.getErrors());
    }

    private void initResultService() {
        String type = this.trainingType.get();

        if (type.equals("Numbers")) {
            resultService = new NumberResultService(common.getEm());
        } else if (type.equals("Words")) {
            resultService = new WordsResultService(common.getEm());
        } else {
            alerts.show(Alerts.alertErr, "Unknown training type");
        }
    }

    private void initDataTable() {
        fxDataTableIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        fxDataTableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        fxDataTableAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        fxDataTableCorrect.setCellValueFactory(new PropertyValueFactory<>("value"));

        fxDataTable.setItems(getDataTableItems());
    }

    private ObservableList<ResultData> getDataTableItems() {
        ObservableList<ResultData> collection = FXCollections.observableArrayList();
        collection.addAll(result.getData());

        return collection;
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

package com.components.MemorizeComponent;

import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingService;
import com.services.TrainingService.WordsTrainingService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
import lib.Component.Component;
import schemas.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemorizeComponent extends Component {

    @FXML VBox fxRoot;
    @FXML VBox fxDataGroup;

    private SimpleIntegerProperty dataCount = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty curDataIndex = new SimpleIntegerProperty(0);
    private SimpleStringProperty curDataItem = new SimpleStringProperty("...");

    private String trainingType;

    private TrainingService trainingService;
    private ArrayList<String> trainingData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(String trainingType, int dataCount) {
        this.trainingType = trainingType;
        this.dataCount.set(dataCount);

        initTrainingServices();
        trainingData = trainingService.start();

        next();
    }

    private void prev() {
        int index = curDataIndex.get() - 1;
        if (index < 1) index = 1;

        curDataIndex.set(index);
        updateDataItem();
    }

    private void next() {
        int index = curDataIndex.get() + 1;
        if (index > dataCount.get()) index = dataCount.get();

        curDataIndex.set(index);
        updateDataItem();
    }

    private void updateDataItem() {
        int index = curDataIndex.get() - 1;
        String evenClassName = "memorize__data-group--even";

        curDataItem.set(trainingData.get(index));

        fxDataGroup.getStyleClass().removeIf(cl -> cl.equals(evenClassName));

        if (index % 2 == 0) {
            fxDataGroup.getStyleClass().add(evenClassName);
        }

    }

    private void initTrainingServices() {
        User user = getUser();
        if (user == null) return;

        var em = common.getEm();

        if (trainingType.equals("Words")) {
            trainingService = new WordsTrainingService(user, em);
            fxRoot.getStyleClass().add("memorize-component--words");

        } else if (trainingType.equals("Numbers")) {
            trainingService = new NumberTrainingService(user, em);
            fxRoot.getStyleClass().add("memorize-component--numbers");

        } else {
            alerts.show(Alerts.alertErr, "Wrong Training Type");
        }

        trainingService.setUp(dataCount.get());
    }

    @FXML private void onNext() {
        next();
    }

    @FXML private void onPrev() {
        prev();
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

    public int getCurDataIndex() {
        return curDataIndex.get();
    }

    public SimpleIntegerProperty curDataIndexProperty() {
        return curDataIndex;
    }

    public void setCurDataIndex(int curDataIndex) {
        this.curDataIndex.set(curDataIndex);
    }

    public String getCurDataItem() {
        return curDataItem.get();
    }

    public SimpleStringProperty curDataItemProperty() {
        return curDataItem;
    }

    public void setCurDataItem(String curDataItem) {
        this.curDataItem.set(curDataItem);
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public TrainingService getTrainingService() {
        return trainingService;
    }

    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    public ArrayList<String> getTrainingData() {
        return trainingData;
    }

    public void setTrainingData(ArrayList<String> trainingData) {
        this.trainingData = trainingData;
    }
}

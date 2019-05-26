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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.MILLIS;

public class MemorizeComponent extends Component {

    @FXML VBox fxRoot;
    @FXML VBox fxDataGroup;

    private SimpleIntegerProperty dataCount = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty curDataIndex = new SimpleIntegerProperty(0);
    private SimpleStringProperty curDataItem = new SimpleStringProperty("...");

    private ArrayList<String> trainingData;
    private String trainingType;

    private ArrayList<Integer> timesToMemorize;
    private LocalDateTime prevTime;

    private OnDone onDone;

    private boolean isDone = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(ArrayList<String> trainingData, String trainingType, OnDone onDone) {
        this.trainingData = trainingData;
        this.dataCount.set(trainingData.size());
        this.trainingType = trainingType;
        this.onDone = onDone;

        timesToMemorize = new ArrayList<>(Collections.nCopies(dataCount.get(), -1));
        prevTime = LocalDateTime.now();

        init();
        next();
    }

    private void init() {
        if (trainingType.equals("Words")) {
            fxRoot.getStyleClass().add("memorize-component--words");

        } else if (trainingType.equals("Numbers")) {
            fxRoot.getStyleClass().add("memorize-component--numbers");

        }
    }

    private void prev() {
        int index = curDataIndex.get() - 1;
        if (index < 1) index = 1;

        curDataIndex.set(index);
        updateDataItem();
    }

    private void next() {
        int index = curDataIndex.get() + 1;

        if (index > dataCount.get()) {
            finishMemorize();
            return;
        }

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

        if (index > 0 && timesToMemorize.get(index - 1) == -1) {
            LocalDateTime curTime = LocalDateTime.now();
            timesToMemorize.set(index - 1, (int) MILLIS.between(prevTime, curTime));
            prevTime = curTime;
        }

        System.out.println(timesToMemorize);
    }

    private void finishMemorize() {
        if (isDone) return;
        isDone = true;

        onDone.call(timesToMemorize);
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

    public void setDataCount(int dataCount) {
        this.dataCount.set(dataCount);
    }

    public SimpleIntegerProperty dataCountProperty() {
        return dataCount;
    }

    public int getCurDataIndex() {
        return curDataIndex.get();
    }

    public void setCurDataIndex(int curDataIndex) {
        this.curDataIndex.set(curDataIndex);
    }

    public SimpleIntegerProperty curDataIndexProperty() {
        return curDataIndex;
    }

    public String getCurDataItem() {
        return curDataItem.get();
    }

    @FunctionalInterface
    public interface OnDone {
        void call(ArrayList<Integer> timesToMemorize);
    }
}

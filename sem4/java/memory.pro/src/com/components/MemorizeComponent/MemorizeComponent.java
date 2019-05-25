package com.components.MemorizeComponent;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lib.Component.Component;
import java.net.URL;
import java.util.ResourceBundle;

public class MemorizeComponent extends Component {

    private SimpleIntegerProperty dataCount = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty curDataIndex = new SimpleIntegerProperty(0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(String trainingType, int dataCount) {
        this.dataCount.set(dataCount);
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
}

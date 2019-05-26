package com.components.ResultComponent;

import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultComponent extends Component {

    private String trainingType;
    private int resultId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(String trainingType, int resultId) {
        this.trainingType = trainingType;
        this.resultId = resultId;

        System.out.println(trainingType);
        System.out.println(resultId);
    }
}

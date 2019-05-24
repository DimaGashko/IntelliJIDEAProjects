package com.services.TrainingService;

import java.util.ArrayList;

public interface ITrainingService {

    /**
     * Set up the training
     * @param template training data template (for example "XX XX XX")
     * @param dataCount the number of data
     */
    void setUp(String template, int dataCount);

    /**
     * Load training data
     * @return training data as string array
     */
    ArrayList<String> loadData();

    /**
     * Finish the training
     * @return result id
     */
    int finishTraining();

}

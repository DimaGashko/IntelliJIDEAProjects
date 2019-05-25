package com.services.TrainingService;

import java.util.ArrayList;

public interface ITrainingService {

    /**
     * Set up the training
     * @param dataCount the number of data
     */
    void setUp(int dataCount);

    /**
     * Start the training
     * @return training data as string array
     */
    ArrayList<String> start();

    /**
     * Finish the training
     * @param answerData data entered by the user
     * @return training result id
     */
    int finish(ArrayList<TrainingResult> answerData);

}

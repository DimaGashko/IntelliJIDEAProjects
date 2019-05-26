package com.services.TrainingService;

import java.util.ArrayList;

public interface ITrainingService {

    /**
     * @param dataCount the number of data
     */
    void setUp(int dataCount);

    /**
     * @return training data as string array
     */
    ArrayList<String> start();

    /**
     * @return result id
     */
    int finish(TrainingResult result);

}

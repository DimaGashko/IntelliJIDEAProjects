package com.services.TrainingService;

import com.Common.Common;

abstract public class TrainingService implements ITrainingService {
    private Common common;

    public TrainingService(Common common) {
        this.common = common;
    }

}

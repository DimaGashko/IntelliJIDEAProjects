package com.services.TrainingService;

import javax.persistence.EntityManager;

abstract public class TrainingService implements ITrainingService {
    protected EntityManager em;
    protected int dataCount;

    public TrainingService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void setUp(String pattern, int dataCount) {
        this.dataCount = dataCount;
    }

}

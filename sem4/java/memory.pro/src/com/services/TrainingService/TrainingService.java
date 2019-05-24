package com.services.TrainingService;

import javax.persistence.EntityManager;

abstract public class TrainingService implements ITrainingService {
    private EntityManager em;

    public TrainingService(EntityManager em) {
        this.em = em;
    }

}

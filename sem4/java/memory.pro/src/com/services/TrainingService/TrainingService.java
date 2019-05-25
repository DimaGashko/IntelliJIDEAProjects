package com.services.TrainingService;

import schemas.User;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;

abstract public class TrainingService implements ITrainingService {
    protected User user;
    protected EntityManager em;
    protected int dataCount;
    protected LocalDate startTime;

    public TrainingService(User user, EntityManager em) {
        this.user = user;
        this.em = em;
    }

    @Override
    public void setUp(int dataCount) {
        this.dataCount = dataCount;
    }

    protected void beforeStart() {
        this.startTime = LocalDate.now();
    }

}

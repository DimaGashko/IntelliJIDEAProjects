package com.services.ResultService;

import dao.NumbersResultDataDao;

import javax.persistence.EntityManager;

public class NumberResultService extends ResultService {
    NumbersResultDataDao resultDataDao;

    public NumberResultService(EntityManager em) {
        super(em);

        resultDataDao = new NumbersResultDataDao(em);
    }

    @Override
    public Result loadResult() {



        return null;
    }





}

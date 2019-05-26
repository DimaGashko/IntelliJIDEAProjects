package com.services.ResultService;

import javax.persistence.EntityManager;

abstract class ResultService implements IResultService {
    protected EntityManager em;

    public ResultService(EntityManager em) {
        this.em = em;
    }
}

package com.services.ResultService;

import dao.WordsResultDataDao;

import javax.persistence.EntityManager;

public class WordsResultServiece extends ResultService {

    WordsResultDataDao resultDataDao;

    public WordsResultServiece(EntityManager em) {
        super(em);

        resultDataDao = new WordsResultDataDao(em);
    }

    @Override
    public Result loadResult(int resultId) {


        return null;
    }

}

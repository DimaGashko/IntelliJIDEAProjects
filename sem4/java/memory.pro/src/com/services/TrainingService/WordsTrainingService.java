package com.services.TrainingService;

import com.Common.Common;
import schemas.Word;

public class WordsTrainingService extends TrainingService {
    private String tempalate;
    private int dataCount;

    public WordsTrainingService(Common common) {
        super(common);
    }

    @Override
    public void setUp(String template, int dataCount) {
        this.tempalate = template;
        this.dataCount = dataCount;
    }

    @Override
    public String[] loadData() {

        return new String[0];
    }

    @Override
    public int finishTraining() {
        return 0;
    }

    private Word loadWords() {

    }

}

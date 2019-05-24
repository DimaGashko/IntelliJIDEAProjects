package com.services.TrainingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dao.WordDao;

import javax.persistence.EntityManager;

public class WordsTrainingService extends TrainingService {
    private String tempalate;
    private int dataCount;
    private List<String> trainingWords;

    private WordDao wordDao;

    public WordsTrainingService(EntityManager em) {
        super(em);

        this.wordDao = new WordDao(em);
    }

    @Override
    public void setUp(String template, int dataCount) {
        this.tempalate = template;
        this.dataCount = dataCount;
    }

    @Override
    public ArrayList<String> loadData() {
        return loadWords();
    }

    @Override
    public int finishTraining() {
        return 0;
    }

    private ArrayList<String> loadWords() {
        var words = wordDao.loadRandomWords(dataCount);
        if (words.size() == dataCount) {
            return words;
        }

        ArrayList<String> res = new ArrayList<>(dataCount);

        while (res.size() < dataCount) {
            for (String word : words) {
                if (res.size() >= dataCount) break;

                res.add(word);
            }
        }

        Collections.shuffle(res);

        return res;
    }

}

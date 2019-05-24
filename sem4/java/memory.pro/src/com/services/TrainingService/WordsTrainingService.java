package com.services.TrainingService;

import java.util.ArrayList;
import java.util.Collections;
import dao.WordDao;

import javax.persistence.EntityManager;

public class WordsTrainingService extends TrainingService {
    private ArrayList<String> trainingWords;

    private WordDao wordDao;

    public WordsTrainingService(EntityManager em) {
        super(em);

        this.wordDao = new WordDao(em);
    }

    @Override
    public ArrayList<String> loadData() {
        trainingWords =  loadWords();
        return trainingWords;
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

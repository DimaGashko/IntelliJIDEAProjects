package com.services.TrainingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import dao.WordDao;
import schemas.*;

import javax.persistence.EntityManager;

public class WordsTrainingService extends TrainingService {
    private ArrayList<Word> trainingWords;

    private WordDao wordDao;

    public WordsTrainingService(User user, EntityManager em) {
        super(user, em);

        this.wordDao = new WordDao(em);
    }

    @Override
    public ArrayList<String> start() {
        beforeStart();

        trainingWords = loadWords();

        return trainingWords.stream().map(Word::getWord)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Result finish(ArrayList<WordsTrainingResult> answerData) {
        WordsResult result = new WordsResult();
        ArrayList<WordsResultData> wordsResultData = getResultData(result, answerData);

        result.setDate(LocalDate.now());
        result.setUser(user);
        result.setGrade(getGrade(wordsResultData));

        return result;
    }

    private ArrayList<WordsResultData> getResultData(WordsResult result, ArrayList<WordsTrainingResult> answerData) {

        var res = answerData.stream().map((userAnswer) -> {
            WordsResultData resultData = new WordsResultData();

            resultData.setWordsResult(result);
            resultData.setAnswer(userAnswer.getValue());
            resultData.setTime(userAnswer.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < answerData.size(); i++) {
            res.get(i).setWord(trainingWords.get(i));
        }

        return res;

    }

    private int getGrade(ArrayList<WordsResultData> resultData) {
        return 0;
    }

    private ArrayList<Word> loadWords() {
        var words = wordDao.loadRandomWords(dataCount);
        if (words.size() == dataCount) {
            return words;
        }

        ArrayList<Word> res = new ArrayList<>(dataCount);

        while (res.size() < dataCount) {
            for (Word word : words) {
                if (res.size() >= dataCount) break;

                res.add(word);
            }
        }

        Collections.shuffle(res);

        return res;
    }

}

package com.services.TrainingService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import dao.WordDao;
import dao.WordsResultDao;
import dao.WordsResultDataDao;
import schemas.*;

import javax.persistence.EntityManager;

public class WordsTrainingService extends TrainingService {
    private ArrayList<Word> trainingWords;

    private WordDao wordDao;
    private WordsResultDataDao resultDataDao;
    private WordsResultDao resultDao;

    public WordsTrainingService(User user, EntityManager em) {
        super(user, em);

        this.wordDao = new WordDao(em);
        this.resultDataDao = new WordsResultDataDao(em);
        this.resultDao = new WordsResultDao(em);
    }

    @Override
    public ArrayList<String> start() {
        beforeStart();

        trainingWords = loadWords();

        return trainingWords.stream().map(Word::getWord).map(w -> w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int finish(TrainingResult answerData) {
        WordsResult result = new WordsResult();

        ArrayList<WordsResultData> resultData = getResultData(answerData, result);
        fillResult(answerData, resultData, result);

        result.setDateTime(startTime);
        result.setUser(user);

        resultDao.add(result);
        resultDataDao.addAll(resultData);

        return result.getId();
    }

    private void fillResult(TrainingResult trainingResult, ArrayList<WordsResultData> resultData, WordsResult result) {
        int grade = WordsResult.calculateGrade(resultData);

        result.setUser(user);
        result.setDateTime(startTime);
        result.setRememberTime(trainingResult.getTimeToRemember());
        result.setGrade(grade);
    }

    private ArrayList<WordsResultData> getResultData(TrainingResult trainingResult, WordsResult result) {
        ArrayList<WordsResultData> resultData = new ArrayList<>(dataCount);

        var answers = trainingResult.getAnswers();
        var memorizeTimes = trainingResult.getTimesToMemorize();

        for (int i = 0; i < dataCount; i++) {
            WordsResultData next = new WordsResultData();

            next.setDataId(i);
            next.setWordsResult(result);
            next.setWord(trainingWords.get(i));
            next.setAnswer(answers.get(i));
            next.setTime(memorizeTimes.get(i));

            resultData.add(next);
        }

        return resultData;
    }

    private ArrayList<Word> loadWords() {
        var words = wordDao.getRandomWords(dataCount);
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

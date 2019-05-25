package com.services.TrainingService;

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
    private WordsResultDataDao wordsResultDataDao;
    private WordsResultDao wordsResultDao;

    public WordsTrainingService(User user, EntityManager em) {
        super(user, em);

        this.wordDao = new WordDao(em);
        this.wordsResultDataDao = new WordsResultDataDao(em);
        this.wordsResultDao = new WordsResultDao(em);
    }

    @Override
    public ArrayList<String> start() {
        beforeStart();

        trainingWords = loadWords();

        return trainingWords.stream().map(Word::getWord)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int finish(ArrayList<TrainingResult> answerData) {
        WordsResult result = new WordsResult();
        ArrayList<WordsResultData> wordsResultData = getResultData(result, answerData);

        int grade = WordsResult.calculateGrade(wordsResultData);

        result.setDate(LocalDate.now());
        result.setUser(user);
        result.setGrade(grade);

        wordsResultDao.add(result);
        wordsResultDataDao.addAll(wordsResultData);

        return result.getId();
    }

    private ArrayList<WordsResultData> getResultData(WordsResult result, ArrayList<TrainingResult> answerData) {

        var res = answerData.stream().map((userAnswer) -> {
            WordsResultData resultData = new WordsResultData();

            resultData.setWordsResult(result);
            resultData.setAnswer(userAnswer.getValue());
            resultData.setTime(userAnswer.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < answerData.size(); i++) {
            res.get(i).setWord(trainingWords.get(i));
            res.get(i).setDataId(i);
        }

        return res;

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

package com.services.TrainingService;

import dao.*;
import schemas.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class NumberTrainingService extends TrainingService {
    private ArrayList<Integer> trainingNumbers;

    private NumbersResultDataDao numbersResultDataDao;
    private NumbersResultDao numbersResultDao;

    public NumberTrainingService(User user, EntityManager em) {
        super(user, em);

        this.numbersResultDataDao = new NumbersResultDataDao(em);
        this.numbersResultDao = new NumbersResultDao(em);
    }

    @Override
    public ArrayList<String> start() {
        beforeStart();

        trainingNumbers = loadWords();

        return trainingNumbers.stream().map(Object::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int finish(ArrayList<TrainingResult> answerData) {
       /* NumbersResult result = new NumbersResult();
        ArrayList<NumbersResultData> numbersResultData = getResultData(result, answerData);

        int grade = NumbersResult.calculateGrade(numbersResultData);

        result.setDate(LocalDate.now());
        result.setUser(user);
        result.setGrade(grade);

        numbersResultData.add(result);
        numbersResultDataDao.addAll(numbersResultData);

        return result.getId();*/
       return 1;
    }

    private ArrayList<WordsResultData> getResultData(WordsResult result, ArrayList<TrainingResult> answerData) {

       /* var res = answerData.stream().map((userAnswer) -> {
            WordsResultData resultData = new WordsResultData();

            resultData.setWordsResult(result);
            resultData.setAnswer(userAnswer.getValue());
            resultData.setTime(userAnswer.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < answerData.size(); i++) {
            res.get(i).setWord(trainingWords.get(i));
        }
*/
        //return res;
        return null;

    }

    private ArrayList<Integer> loadWords() {
        ArrayList<Integer> res = new ArrayList<>(dataCount);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < dataCount; i++) {
            Integer r = rand.nextInt(90) + 10;
            res.add(r);
        }

        return res;
    }

}

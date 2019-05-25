package com.services.TrainingService;

import dao.NumbersResultDao;
import dao.NumbersResultDataDao;
import schemas.NumbersResult;
import schemas.NumbersResultData;
import schemas.User;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
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
        NumbersResult result = new NumbersResult();
        ArrayList<NumbersResultData> numbersResultData = getResultData(result, answerData);

        int grade = NumbersResult.calculateGrade(numbersResultData);

        result.setDateTime(startTime);
        result.setUser(user);
        result.setGrade(grade);

        numbersResultDao.add(result);
        numbersResultDataDao.addAll(numbersResultData);

        return result.getId();
    }

    private ArrayList<NumbersResultData> getResultData(NumbersResult result, ArrayList<TrainingResult> answerData) {

        var res = answerData.stream().map((userAnswer) -> {
            NumbersResultData resultData = new NumbersResultData();

            int answer;

            try {
                answer = Integer.parseInt(userAnswer.getValue());
            } catch (NumberFormatException e) {
                answer = -1;
            }

            resultData.setNumbersResult(result);
            resultData.setAnswer(answer);
            resultData.setTime(userAnswer.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < answerData.size(); i++) {
            res.get(i).setNumber(trainingNumbers.get(i));
            res.get(i).setDataId(i);
        }

        return res;
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

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

    private NumbersResultDataDao resultDataDao;
    private NumbersResultDao resultDao;

    public NumberTrainingService(User user, EntityManager em) {
        super(user, em);

        this.resultDataDao = new NumbersResultDataDao(em);
        this.resultDao = new NumbersResultDao(em);
    }

    @Override
    public ArrayList<String> start() {
        beforeStart();

        trainingNumbers = loadWords();

        return trainingNumbers.stream().map(Object::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int finish(TrainingResult answerData) {
        NumbersResult result = new NumbersResult();

        ArrayList<NumbersResultData> resultData = getResultData(answerData, result);
        fillResult(answerData, resultData, result);

        result.setDateTime(startTime);
        result.setUser(user);

        if (result.getGrade() <= 0) {
            return -1;
        }

        resultDao.add(result);
        resultDataDao.addAll(resultData);

        return result.getId();
    }

    private void fillResult(TrainingResult trainingResult, ArrayList<NumbersResultData> resultData, NumbersResult result) {
        int grade = NumbersResult.calculateGrade(resultData);

        result.setUser(user);
        result.setDateTime(startTime);
        result.setRememberTime(trainingResult.getTimeToRemember());
        result.setGrade(grade);
    }

    private ArrayList<NumbersResultData> getResultData(TrainingResult trainingResult, NumbersResult result) {
        ArrayList<NumbersResultData> resultData = new ArrayList<>(dataCount);

        var answers = trainingResult.getAnswers();
        var memorizeTimes = trainingResult.getTimesToMemorize();

        for (int i = 0; i < dataCount; i++) {
            NumbersResultData next = new NumbersResultData();
            int answer = parseAnswer((answers.get(i)));

            next.setDataId(i);
            next.setNumbersResult(result);
            next.setNumber(trainingNumbers.get(i));
            next.setAnswer(answer);
            next.setTime(memorizeTimes.get(i));

            resultData.add(next);
        }

        return resultData;
    }

    private int parseAnswer(String strAns) {
        int answer;

        try {
            answer = Integer.parseInt(strAns);
        } catch (NumberFormatException e) {
            answer = -1;
        }

        return answer;
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

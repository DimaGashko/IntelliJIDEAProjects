package com.services.ResultService;

import dao.NumbersResultDataDao;
import schemas.NumbersResultData;
import schemas.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class NumberResultService extends ResultService {
    NumbersResultDataDao resultDataDao;

    public NumberResultService(EntityManager em) {
        super(em);

        resultDataDao = new NumbersResultDataDao(em);
    }

    @Override
    public Result loadResult(int resultId) {
        var rawResultData = resultDataDao.getById(resultId);
        var resultData = getResultData(rawResultData);

        return createResult(resultData, rawResultData);
    }

    private Result createResult(ArrayList<ResultData> resultData, ArrayList<NumbersResultData> rawResultData) {
        Result result = new Result();
        var numbersResult = rawResultData.get(0).getNumbersResult();

        User user = rawResultData.get(0).getNumbersResult().getUser();

        result.setId(rawResultData.get(0).getId());
        result.setUsername(user.getUsername());

        result.setGrade(numbersResult.getGrade());
        result.setDataCount(resultData.size());

        result.setStartTime(numbersResult.getDateTime());
        result.setMemorizeTime(getMemorizeTime(resultData));
        result.setRememberTime(numbersResult.getRememberTime());

        result.setMinMemorizeTime(getMinMemorizeTime(resultData));
        result.setMaxMemorizeTime(getMaxMemorizeTime(resultData));
        result.setAvgMemorizeTime(getAvgMemorizeTime(resultData));

        result.setCorrectAns(getCorrectAns(resultData));

        result.setData(resultData);

        return result;
    }

    private ArrayList<ResultData> getResultData(ArrayList<NumbersResultData> rawResultData) {

        return rawResultData.stream().map((item) -> {
            ResultData resultData = new ResultData();

            resultData.setValue(Integer.toString(item.getNumber()));
            resultData.setAnswer(Integer.toString(item.getAnswer()));
            resultData.setTime(item.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

    }

    private int getMemorizeTime(ArrayList<ResultData> resultData) {
        int millis = resultData.stream().map(ResultData::getTime).reduce(0, Integer::sum);

        return round(millis / 1000.f);
    }

    private int getMinMemorizeTime(ArrayList<ResultData> resultData) {
        return resultData.stream().map(ResultData::getTime)
                .min(Comparator.comparingInt(a -> a)).orElse(0);
    }

    private int getMaxMemorizeTime(ArrayList<ResultData> resultData) {
        return resultData.stream().map(ResultData::getTime)
                .max(Comparator.comparingInt(a -> a)).orElse(0);
    }

    private int getAvgMemorizeTime(ArrayList<ResultData> resultData) {
        return getMaxMemorizeTime(resultData) / resultData.size();
    }

    private int getCorrectAns(ArrayList<ResultData> resultData) {
        return (int)resultData.stream().filter(ResultData::isCorrect).count();
    }


}

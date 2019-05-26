package com.services.ResultService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Math.round;

abstract class ResultService implements IResultService {
    protected EntityManager em;

    public ResultService(EntityManager em) {
        this.em = em;
    }

    protected int getMemorizeTime(ArrayList<ResultData> resultData) {
        int millis = resultData.stream().map(ResultData::getTime).reduce(0, Integer::sum);

        return round(millis / 1000.f);
    }

    protected int getMinMemorizeTime(ArrayList<ResultData> resultData) {
        return resultData.stream().map(ResultData::getTime)
                .min(Comparator.comparingInt(a -> a)).orElse(0);
    }

    protected int getMaxMemorizeTime(ArrayList<ResultData> resultData) {
        return resultData.stream().map(ResultData::getTime)
                .max(Comparator.comparingInt(a -> a)).orElse(0);
    }

    protected int getAvgMemorizeTime(ArrayList<ResultData> resultData) {
        return getMaxMemorizeTime(resultData) / resultData.size();
    }

    protected int getCorrectAns(ArrayList<ResultData> resultData) {
        return (int)resultData.stream().filter(ResultData::isCorrect).count();
    }

}

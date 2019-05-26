package com.services.ResultService;

import dao.WordsResultDataDao;
import schemas.User;
import schemas.WordsResultData;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class WordsResultServiece extends ResultService {

    WordsResultDataDao resultDataDao;

    public WordsResultServiece(EntityManager em) {
        super(em);

        resultDataDao = new WordsResultDataDao(em);
    }

    @Override
    public Optional<Result> loadResult(int resultId) {
        var rawResultData = resultDataDao.getById(resultId);
        if (rawResultData.isEmpty()) {
            return Optional.empty();
        }

        var resultData = getResultData(rawResultData);
        var result = createResult(resultData, rawResultData);

        return Optional.of(result);
    }

    private Result createResult(ArrayList<ResultData> resultData, ArrayList<WordsResultData> rawResultData) {
        Result result = new Result();
        var numbersResult = rawResultData.get(0).getWordsResult();

        User user = rawResultData.get(0).getWordsResult().getUser();

        result.setUsername(user.getUsername());
        result.setId(rawResultData.get(0).getId());

        result.setStartTime(numbersResult.getDateTime());
        result.setMemorizeTime(getMemorizeTime(resultData));
        result.setRememberTime(numbersResult.getRememberTime());

        result.setGrade(numbersResult.getGrade());
        result.setDataCount(resultData.size());

        result.setMinMemorizeTime(getMinMemorizeTime(resultData));
        result.setMaxMemorizeTime(getMaxMemorizeTime(resultData));
        result.setAvgMemorizeTime(getAvgMemorizeTime(resultData));

        result.setCorrectAns(getCorrectAns(resultData));

        result.setData(resultData);

        return result;
    }

    private ArrayList<ResultData> getResultData(ArrayList<WordsResultData> rawResultData) {

        return rawResultData.stream().map((item) -> {
            ResultData resultData = new ResultData();

            resultData.setValue(item.getWord().getWord());
            resultData.setAnswer(item.getAnswer());
            resultData.setTime(item.getTime());

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

    }


}

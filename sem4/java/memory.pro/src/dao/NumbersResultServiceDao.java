package dao;

import com.services.ResultService.Result;
import com.services.ResultService.ResultData;
import schemas.NumbersResultData;
import schemas.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class NumbersResultServiceDao extends Dao {

    public NumbersResultServiceDao(EntityManager em) {
        super(em);
    }

    public Optional<Result> getResultById(int id) {
        String sql = "select d from NumbersResultData d where d.numbersResult.id = :id order by d.dataId";
        var query = em.createQuery(sql, NumbersResultData.class);
        query.setParameter("id", id);

        ArrayList<NumbersResultData> numbersResultData = new ArrayList(query.getResultList());
        if (numbersResultData.isEmpty()) return Optional.empty();

        ArrayList<ResultData> resultData = getResultData(numbersResultData);
        Result result = createResult(resultData, numbersResultData);

        return  Optional.of(result);
    }

    private Result createResult(ArrayList<ResultData> resultData, ArrayList<NumbersResultData> numbersResultData) {
        Result result = new Result();
        var numbersResult = numbersResultData.get(0).getNumbersResult();

        User user = numbersResultData.get(0).getNumbersResult().getUser();

        result.setId(numbersResultData.get(0).getId());
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

    private ArrayList<ResultData> getResultData(ArrayList<NumbersResultData> numbersResultData) {

        return numbersResultData.stream().map((item) -> {
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

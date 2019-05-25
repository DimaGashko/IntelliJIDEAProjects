package schemas;

import javax.persistence.Entity;
import java.util.ArrayList;

import static java.lang.Math.round;

@Entity
public class WordsResult extends Result {

    static public int calculateGrade(ArrayList<WordsResultData> resultData) {
        int time = 0;
        int correct = 0;

        for (WordsResultData item : resultData) {
            time += item.time;
            correct += item.getWord().getWord().equals(item.getAnswer()) ? 1 : 0;
        }

        float grade = (correct + (resultData.size() - time / 3.f)) / 5.f;
        return round(grade);
    }

}

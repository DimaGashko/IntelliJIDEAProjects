package schemas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class WordsResultData extends ResultData {

    @ManyToOne
    private WordsResultData wordsResultData;

    public WordsResultData getWordsResultData() {
        return wordsResultData;
    }

    public void setWordsResultData(WordsResultData wordsResultData) {
        this.wordsResultData = wordsResultData;
    }
}

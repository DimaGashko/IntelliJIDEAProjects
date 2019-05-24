package schemas;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class WordsResult extends Result {

    @OneToMany
    private Collection<WordsResultData> wordsResultData;

    public Collection<WordsResultData> getWordsResultData() {
        return wordsResultData;
    }

    public void setWordsResultData(Collection<WordsResultData> wordsResultData) {
        this.wordsResultData = wordsResultData;
    }
}

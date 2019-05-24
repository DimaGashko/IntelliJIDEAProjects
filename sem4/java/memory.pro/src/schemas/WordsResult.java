package schemas;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class WordsResult extends Result {

    @OneToMany
    private Collection<WordsResultData> wordResultsData;

    public Collection<WordsResultData> getWordResultsData() {
        return wordResultsData;
    }

    public void setWordResultsData(Collection<WordsResultData> wordResultsData) {
        this.wordResultsData = wordResultsData;
    }
}

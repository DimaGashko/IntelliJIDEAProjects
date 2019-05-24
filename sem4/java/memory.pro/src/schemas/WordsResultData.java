package schemas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class WordsResultData extends ResultData {

    @ManyToOne
    private WordsResultData wordsResultData;

    @OneToMany
    private Collection<Word> words;

    public WordsResultData getWordsResultData() {
        return wordsResultData;
    }

    public void setWordsResultData(WordsResultData wordsResultData) {
        this.wordsResultData = wordsResultData;
    }

    public Collection<Word> getWords() {
        return words;
    }

    public void setWords(Collection<Word> words) {
        this.words = words;
    }
}

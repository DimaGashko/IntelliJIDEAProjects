package schemas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class WordsResultData extends ResultData {

    @ManyToOne
    private WordsResult wordsResult;

    @OneToMany
    private Collection<Word> words;

    @OneToMany
    private Collection<Word> answer;

    public WordsResult getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(WordsResult wordsResult) {
        this.wordsResult = wordsResult;
    }

    public Collection<Word> getWords() {
        return words;
    }

    public void setWords(Collection<Word> words) {
        this.words = words;
    }

    public Collection<Word> getAnswer() {
        return answer;
    }

    public void setAnswer(Collection<Word> answer) {
        this.answer = answer;
    }
}

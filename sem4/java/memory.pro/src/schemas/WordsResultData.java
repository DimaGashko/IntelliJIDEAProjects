package schemas;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class WordsResultData extends ResultData {

    @ManyToOne
    private WordsResult wordsResult;

    @OneToOne
    private Word word;

    @Column(name = "answer", nullable = false)
    private String answer;

    public WordsResult getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(WordsResult wordsResult) {
        this.wordsResult = wordsResult;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "WordsResultData{" +
                "wordsResult=" + wordsResult +
                ", word=" + word +
                ", answer='" + answer + '\'' +
                ", id=" + id +
                ", time=" + time +
                '}';
    }
}

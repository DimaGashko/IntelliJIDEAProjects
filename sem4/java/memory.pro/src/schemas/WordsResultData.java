package schemas;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class WordsResultData {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "data_id", nullable = false)
    protected int dataId;

    @Column(name = "time", nullable = false)
    protected int time;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordsResultData that = (WordsResultData) o;
        return id == that.id &&
                time == that.time &&
                Objects.equals(wordsResult, that.wordsResult) &&
                Objects.equals(word, that.word) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, wordsResult, word, answer);
    }

    @Override
    public String toString() {
        return "WordsResultData{" +
                "id=" + id +
                ", time=" + time +
                ", wordsResult=" + wordsResult +
                ", word=" + word +
                ", answer='" + answer + '\'' +
                '}';
    }
}

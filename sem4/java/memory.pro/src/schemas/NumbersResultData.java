package schemas;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class NumbersResultData {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "data_id", nullable = false)
    protected int dataId;

    @Column(name = "time", nullable = false)
    protected int time;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(nullable = false)
    private int answer;

    @ManyToOne
    private NumbersResult numbersResult;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public NumbersResult getNumbersResult() {
        return numbersResult;
    }

    public void setNumbersResult(NumbersResult numbersResult) {
        this.numbersResult = numbersResult;
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
        NumbersResultData that = (NumbersResultData) o;
        return id == that.id &&
                time == that.time &&
                number == that.number &&
                answer == that.answer &&
                Objects.equals(numbersResult, that.numbersResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, number, answer, numbersResult);
    }

    @Override
    public String toString() {
        return "NumbersResultData{" +
                "id=" + id +
                ", time=" + time +
                ", number=" + number +
                ", answer=" + answer +
                ", numbersResult=" + numbersResult +
                '}';
    }
}

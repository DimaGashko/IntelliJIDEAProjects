package schemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class NumbersResultData extends ResultData {

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "answer", nullable = false)
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
}

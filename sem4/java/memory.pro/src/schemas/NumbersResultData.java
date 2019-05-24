package schemas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class NumbersResultData extends ResultData {

    @ManyToOne
    private NumbersResult numbersResult;

    public NumbersResult getNumbersResult() {
        return numbersResult;
    }

    public void setNumbersResult(NumbersResult numbersResult) {
        this.numbersResult = numbersResult;
    }
}

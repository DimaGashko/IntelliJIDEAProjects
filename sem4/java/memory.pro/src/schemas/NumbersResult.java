package schemas;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class NumbersResult extends Result {

    @OneToMany
    private Collection<NumbersResultData> numbersResultData;

    public Collection<NumbersResultData> getNumbersResultData() {
        return numbersResultData;
    }

    public void setNumbersResultData(Collection<NumbersResultData> numbersResultData) {
        this.numbersResultData = numbersResultData;
    }
}

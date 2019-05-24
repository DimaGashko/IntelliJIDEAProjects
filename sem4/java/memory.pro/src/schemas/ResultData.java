package schemas;

import javax.persistence.*;
import java.util.Objects;

@Entity
abstract public class ResultData {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "time", nullable = false)
    protected int time;

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected int getTime() {
        return time;
    }

    protected void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultData that = (ResultData) o;
        return id == that.id &&
                time == that.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }
}

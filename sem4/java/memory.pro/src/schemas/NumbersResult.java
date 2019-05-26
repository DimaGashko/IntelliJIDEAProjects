package schemas;

import dao.NumbersResultDao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static java.lang.Math.*;

@Entity
public class NumbersResult {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "date_time", nullable = false)
    protected LocalDateTime dateTime;

    @Column(name = "remember_time", nullable = false)
    protected int rememberTime;

    @Column(name = "grade", nullable = false)
    protected int grade;

    @ManyToOne
    protected User user;

    static public int calculateGrade(ArrayList<NumbersResultData> resultData) {
        int time = 0;
        int correct = 0;

        for (NumbersResultData item : resultData) {
            time += item.time;
            correct += item.getNumber() == item.getAnswer() ? 1 : 0;
        }

        time /= 1000;

        float grade = correct + (resultData.size() - time / 3.f);
        return max(round(grade), 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRememberTime() {
        return rememberTime;
    }

    public void setRememberTime(int rememberTime) {
        this.rememberTime = rememberTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumbersResult that = (NumbersResult) o;
        return id == that.id &&
                rememberTime == that.rememberTime &&
                grade == that.grade &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, rememberTime, grade, user);
    }

    @Override
    public String toString() {
        return "NumbersResult{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", rememberTime=" + rememberTime +
                ", grade=" + grade +
                ", user=" + user +
                '}';
    }
}

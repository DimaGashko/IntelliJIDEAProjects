package schemas;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.round;

@Entity
public class WordsResult {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "date_time", nullable = false)
    protected LocalDateTime dateTime;

    @Column(name = "grade", nullable = false)
    protected int grade;

    @Column(name = "remember_time", nullable = false)
    protected int rememberTime;

    @ManyToOne
    protected User user;

    static public int calculateGrade(ArrayList<WordsResultData> resultData) {
        int time = 0;
        int correct = 0;

        for (WordsResultData item : resultData) {
            time += item.time;
            correct += item.getWord().getWord().equalsIgnoreCase(item.getAnswer()) ? 1 : 0;
        }

        float grade = correct + (resultData.size() - time / 3.f);
        return round(grade);
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
        WordsResult that = (WordsResult) o;
        return id == that.id &&
                grade == that.grade &&
                rememberTime == that.rememberTime &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, grade, rememberTime, user);
    }

    @Override
    public String toString() {
        return "WordsResult{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", grade=" + grade +
                ", rememberTime=" + rememberTime +
                ", user=" + user +
                '}';
    }
}

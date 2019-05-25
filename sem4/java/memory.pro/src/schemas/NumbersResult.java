package schemas;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class NumbersResult {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "date", nullable = false)
    protected LocalDate date;

    @Column(name = "grade", nullable = false)
    protected int grade;

    @ManyToOne
    protected User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumbersResult that = (NumbersResult) o;
        return id == that.id &&
                grade == that.grade &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, grade, user);
    }

    @Override
    public String toString() {
        return "NumbersResult{" +
                "id=" + id +
                ", date=" + date +
                ", grade=" + grade +
                ", user=" + user +
                '}';
    }
}

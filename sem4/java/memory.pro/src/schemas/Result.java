package schemas;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
abstract public class Result {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "grade", nullable = false)
    private int grade;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setUser(User users) {
        this.user = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return id == result.id &&
                userId == result.userId &&
                grade == result.grade &&
                Objects.equals(date, result.date) &&
                Objects.equals(user, result.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, grade, user);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", grade=" + grade +
                ", users=" + user.getUsername() + // TODO: check if user always exist
                '}';
    }
}

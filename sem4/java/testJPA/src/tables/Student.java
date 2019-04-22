package tables;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(fio, student.fio) &&
                Objects.equals(age, student.age);
    }

    @Override
    public String toString() {
        return id + ": " + fio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, age);
    }

    @ManyToMany(mappedBy = "students")
    private Collection<Subject> subjects;

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }
}

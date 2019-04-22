package dao;

import tables.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDAO {
    private EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public void add(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    public void delete(Student student) {
        em.getTransaction().begin();
        Student forDelete = em.find(Student.class, student.getId());
        em.remove(forDelete);
        em.getTransaction().commit();
    }
}

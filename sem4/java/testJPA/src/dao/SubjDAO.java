package dao;

import tables.Subject;

import javax.persistence.EntityManager;
import java.util.List;

public class SubjDAO {
    private EntityManager em;

    public SubjDAO(EntityManager em) {
        this.em = em;
    }

    public List<Subject> findAll() {
        return em.createQuery("select s from Subject s", Subject.class)
                .getResultList();
    }

    public void add(Subject subject) {
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
    }
}

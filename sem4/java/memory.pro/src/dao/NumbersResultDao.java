package dao;

import schemas.NumbersResult;

import javax.persistence.EntityManager;

public class NumbersResultDao extends Dao {

    public NumbersResultDao(EntityManager em) {
        super(em);
    }

    public void add(NumbersResult result) {
        em.getTransaction().begin();
        em.persist(result);
        em.getTransaction().commit();
    }

}

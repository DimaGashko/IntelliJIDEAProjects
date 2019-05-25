package dao;

import schemas.WordsResult;

import javax.persistence.EntityManager;

public class WordsResultDao extends Dao {

    public WordsResultDao(EntityManager em) {
        super(em);
    }

    public void add(WordsResult result) {
        em.getTransaction().begin();
        em.persist(result);
        em.getTransaction().commit();
    }

}

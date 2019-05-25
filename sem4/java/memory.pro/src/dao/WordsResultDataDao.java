package dao;

import schemas.WordsResultData;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class WordsResultDataDao extends Dao {

    public WordsResultDataDao(EntityManager em) {
        super(em);
    }

    public void addAll(ArrayList<WordsResultData> resultData) {
        em.getTransaction().begin();
        resultData.forEach(item -> em.persist(item));
        em.getTransaction().commit();
    }

}

package dao;

import schemas.NumbersResultData;
import schemas.WordsResultData;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class NumbersResultDataDao extends Dao {

    public NumbersResultDataDao(EntityManager em) {
        super(em);
    }

    public void addAll(ArrayList<NumbersResultData> resultData) {
        em.getTransaction().begin();
        resultData.forEach(item -> em.persist(item));
        em.getTransaction().commit();
    }

}

package dao;

import schemas.NumbersResultData;
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

    public  ArrayList<WordsResultData> getById(int id) {
        String sql = "select d from NumbersResultData d where d.numbersResult.id = :id order by d.dataId";
        var query = em.createQuery(sql, WordsResultData.class);
        query.setParameter("id", id);

        return new ArrayList(query.getResultList());
    }

}

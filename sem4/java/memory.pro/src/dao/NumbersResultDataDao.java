package dao;

import schemas.NumbersResultData;
import schemas.WordsResultData;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

public class NumbersResultDataDao extends Dao {

    public NumbersResultDataDao(EntityManager em) {
        super(em);
    }

    public void addAll(ArrayList<NumbersResultData> resultData) {
        em.getTransaction().begin();
        resultData.forEach(item -> em.persist(item));
        em.getTransaction().commit();
    }

    public  ArrayList<NumbersResultData> getById(int id) {
        String sql = "select d from NumbersResultData d where d.numbersResult.id = :id order by d.dataId";
        var query = em.createQuery(sql, NumbersResultData.class);
        query.setParameter("id", id);

        return new ArrayList(query.getResultList());
    }

}

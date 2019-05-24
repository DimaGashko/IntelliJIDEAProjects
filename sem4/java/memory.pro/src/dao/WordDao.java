package dao;

import javax.persistence.EntityManager;
import java.util.List;

public class WordDao extends Dao {

    public WordDao(EntityManager em) {
        super(em);
    }

    public List<String> loadRandomWords(int limit) {
        var query = em.createNativeQuery("select word from WORD order by RAND()");
        return query.setMaxResults(limit).getResultList();
    }

}

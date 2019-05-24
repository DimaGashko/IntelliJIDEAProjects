package dao;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class WordDao extends Dao {

    public WordDao(EntityManager em) {
        super(em);
    }

    public ArrayList<String> loadRandomWords(int limit) {
        var query = em.createNativeQuery("select word from WORD order by RAND()");
        var words = query.setMaxResults(limit).getResultList();

        return new ArrayList<>(words);
    }

}

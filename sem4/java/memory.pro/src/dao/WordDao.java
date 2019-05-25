package dao;

import schemas.Word;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class WordDao extends Dao {

    public WordDao(EntityManager em) {
        super(em);
    }

    public ArrayList<Word> getRandomWords(int limit) {
        var query = em.createNativeQuery("select * from WORD order by RAND()", Word.class);
        var words = query.setMaxResults(limit).getResultList();

        return new ArrayList<>(words);
    }

}

package dao;

import javax.persistence.EntityManager;

public class WordDao extends Dao {

    public WordDao(EntityManager em) {
        super(em);
    }

}

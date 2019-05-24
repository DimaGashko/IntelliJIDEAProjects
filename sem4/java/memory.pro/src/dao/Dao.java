package dao;

import javax.persistence.EntityManager;

abstract public class Dao {
    protected EntityManager em;

    public Dao(EntityManager em) {
        this.em = em;
    }
}

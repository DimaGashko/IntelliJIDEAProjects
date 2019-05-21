package dao;

import schemas.User;

import javax.persistence.EntityManager;

public class UserDao {
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public void add(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        var toDelete = em.find(User.class, id);
        em.remove(toDelete);
        em.getTransaction().commit();
    }

}

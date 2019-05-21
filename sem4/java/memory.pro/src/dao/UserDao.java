package dao;

import schemas.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Optional<String> getSaltByUsername(String username) {
        var query = em.createQuery("select u.passwordSalt from User u where u.username = :username", String.class);
        query.setParameter("username", username);

        var salts = query.setMaxResults(1).getResultList();

        if (salts.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(salts.get(0));
    }

    public boolean checkExist(String username) {
        var query = em.createQuery("select COUNT(u.id) from User u where u.username = :username", Long.class);
        query.setParameter("username", username);

        long keysCount = query.setMaxResults(1).getResultList().get(0);

        return keysCount != 0;
    }

    public boolean checkKey(String username, String key) {
        var query = em.createQuery("select COUNT(u.passwordKey) from User u where u.username = :username and u.passwordKey = :key", Long.class);
        query.setParameter("username", username);
        query.setParameter("key", key);

        long keysCount = query.setMaxResults(1).getResultList().get(0);

        return keysCount != 0;
    }

}

package dao;

import schemas.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserDao extends Dao {

    public UserDao(EntityManager em) {
        super(em);
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

    public Optional<User> loadByUsername(String username) {
        var query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);

        var user = query.getSingleResult();

        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    public Optional<String> loadSaltByUsername(String username) {
        var query = em.createQuery("select u.passwordSalt from User u where u.username = :username", String.class);
        query.setParameter("username", username);

        var salt = query.getSingleResult();

        if (salt == null || salt.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(salt);
    }

    public boolean checkExist(User user) {
        var query = em.createQuery("select COUNT(u.id) from User u where u.username = :username or u.email = :email or u.id = :id", Long.class);
        query.setParameter("username", user.getUsername());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());

        long keysCount = query.getSingleResult();

        return keysCount != 0;
    }

    public boolean checkKey(String username, String key) {
        var query = em.createQuery("select COUNT(u.passwordKey) from User u where u.username = :username and u.passwordKey = :key", Long.class);
        query.setParameter("username", username);
        query.setParameter("key", key);

        long keysCount = query.getSingleResult();

        return keysCount != 0;
    }

}

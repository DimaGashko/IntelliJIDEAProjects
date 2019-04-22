package dao;

import schemas.Book;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDao {
    private EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public List<Book> findAll(int limit) {
        return em.createQuery("select b from Book b", Book.class)
                .setMaxResults(limit)
                .getResultList();
    }

    public void add(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        var toDelete = em.find(Book.class, id);
        em.remove(toDelete);
        em.getTransaction().commit();
    }

    public void delete(Book book) {
        delete(book.getId());
    }

}

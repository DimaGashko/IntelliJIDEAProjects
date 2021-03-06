package dao;

import schemas.Book;

import javax.persistence.EntityManager;
import java.time.LocalDate;
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

    public List<Book> findAllByAuthor(String author, int limit) {
        var query = em.createQuery("select b from Book b where b.author like :author order by b.publishDate", Book.class);
        query.setParameter("author", "%" + author + "%");

        return query
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Book> findAllByPublisher(String publisher, int limit) {
        var query = em.createQuery("select b from Book b where b.publisher like :publisher", Book.class);
        query.setParameter("publisher", "%" + publisher + "%");

        return query
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Book> findAllByDate(LocalDate date, int limit) {
        var query = em.createQuery("select b from Book b where b.publishDate > :date", Book.class);
        query.setParameter("date", date);

        return query
                .setMaxResults(limit)
                .getResultList();
    }

    public List<String> findAllAuthors(int limit) {
        return em.createQuery("select distinct b.author from Book b order by b.author", String.class)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<String> findAllPublishers(int limit) {
        return em.createQuery("select distinct b.publisher from Book b", String.class)
                .setMaxResults(limit)
                .getResultList();
    }

    public void delete(Book book) {
        delete(book.getId());
    }

}


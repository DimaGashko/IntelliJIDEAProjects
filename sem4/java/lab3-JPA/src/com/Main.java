package com;

import dao.BookDao;
import schemas.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        BookDao bookDao = new BookDao(em);

        for (int i = 1; i < 4000; i++) {
            Book book = new Book();
            book.setId(i);

            bookDao.delete(book);

        }

        var books = bookDao.findAll();
        books.forEach(System.out::println);

        System.out.println("Hello");
    }

}

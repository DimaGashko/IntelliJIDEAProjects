package com.labs.lab3.part1;

import com.labs.lab3.part1.library.*;
import static com.helpers.console.ConsolePrompt.*;
import static com.helpers.console.ConsoleElements.hr;

public class Lab3Part1 {
   private Library library = new Library();

   public static void main(String[] args) {
      var app = new Lab3Part1();
      app.run();
   }

   public void run() {
      Book[] books;

      var type = promptInt(
            "Enter \"1\" if you want to enter books yourself, " + "something else to use a test books: ");

      if (type == 1)
         books = getBooksFromConsole();
      else
         books = getTestBooks();

      library.addBooks(books);
      runCLI();
   }

   private void runCLI() {
      printHelp();

      while (true) {
         String command = promptLine("Enter command: ");

         if (command.equalsIgnoreCase("exit"))
            break;
         else
            useCommand(command);

         hr();
      }

   }

   private void useCommand(String command) {
      if (command.equalsIgnoreCase("author")) {
         String author = promptLine("Enter the author: ");
         printBooks(library.getBooksByAuthor(author));

      } else if (command.equalsIgnoreCase("publisher")) {
         String publisher = promptLine("Enter the publisher: ");
         printBooks(library.getBooksByPublisher(publisher));

      } else if (command.equalsIgnoreCase("afterYear")) {
         int year = promptInt("Enter the year: ");
         printBooks(library.getBooksAfterYear(year));

      }

      else if (command.equalsIgnoreCase("all")) {
         printBooks(library.getBooks());

      } else if (command.equalsIgnoreCase("help")) {
         printHelp();

      } else {
         System.out.println("Command not found. Try again: ");
      }
   }

   private Book[] getBooksFromConsole() {
      var number = promptInt("Enter the number of books: ");
      Book[] books = new Book[number];

      hr();

      for (int i = 0; i < books.length; i++) {
         String name = promptLine("Enter the name of the book: ");
         String author = promptLine("Enter the author: ");
         String publisher = promptLine("Enter the publisher: ");
         int year = promptInt("Enter the year: ");
         int pages = promptInt("Enter the number of pages: ");
         double price = promptDouble("Enter the price: ");

         books[i] = new Book(name, author, publisher, year, pages, price);

         hr();
      }

      return books;
   }

   private Book[] getTestBooks() {
      Book[] books = new Book[7];

      books[0] = new Book("JavaScript: The Definitive Guide", "David Flanagan", "O'Reilly", 2006, 999, 50);
      books[1] = new Book("Java Programming", "Donald Bales", "O'Reilly", 2001, 450, 60);
      books[2] = new Book("Thinking in Java", "Bruce Eckel", "Oracle", 2002, 328, 50);
      books[3] = new Book("JS.Next", "Aaron Frost", "O'Reilly", 2015, 250, 50);
      books[4] = new Book("You don't know JS", "Simpson K.", "O'Reilly", 2015, 88, 30);
      books[5] = new Book("Angular", "David Flanagan", "O'Reilly", 2016, 396, 30);
      books[6] = new Book("Java in a Nutshell", "David Flanagan", "O'Reilly", 2014, 396, 60);

      return books;
   }

   private void printBooks(Book[] books) {
      if (books.length == 0) {
         System.out.println("There's no book");
      }

      for (Book book : books) {
         System.out.println(book);
      }
   }

   private void printHelp() {
      System.out.println("Commands:");
      System.out.println("> all #Print all books");
      System.out.println("> author #Print books by Author");
      System.out.println("> publisher #Print books by Publisher");
      System.out.println("> afterYear #Print books published after year");
      System.out.println();
      System.out.println("> help #Print Help");
      System.out.println("> exit #Exit");

      hr();
   }
}

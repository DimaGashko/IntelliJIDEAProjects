package learn.other.ArrayList;

import com.labs.lab3.part1.library.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class readArrayList {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Book> arr = new ArrayList<>();
        /*arr.add(new Book("a", "a", "a", 1,1,1));
        arr.add(new Book("b", "b", "b", 2,2,2));
        arr.add(new Book("c", "c", "c", 3,3,3));

        try(FileOutputStream fis = new FileOutputStream("test");
            ObjectOutputStream ois = new ObjectOutputStream(fis)) {

            ois.writeObject(arr);

        }*/

        ArrayList<Book> arr2 = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            ArrayList<Book> loaded = (ArrayList<Book>)ois.readObject();
            if (loaded != null) arr2 = loaded;

        } catch (EOFException err) {
            System.out.println("No Data");
        }

        System.out.println(arr.stream().map(Book::getName).collect(Collectors.joining(" ")));
        System.out.println(arr2.stream().map(Book::getName).collect(Collectors.joining(" ")));

    }

    static private class Test implements Serializable {
        public int a;
        public int b;

        public Test() {

        }

        public Test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return Integer.toString(this.a) + "|" + Integer.toString(this.b);
        }
    }

}

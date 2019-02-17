package learn.Collections;

import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {

        Set<Integer> m = new HashSet<>();

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("User1", 10));
        users.add(new User("User2", 65));
        users.add(new User("User3", 15));
        users.add(new User("User4", 18));
        users.add(new User("User5", 13));
        users.add(new User("User6", 24));
        users.add(new User("User7", 36));
        users.add(new User("User8", 25));
        users.add(new User("User9", 19));
        users.add(new User("User10", 17));
        users.add(new User("User11", 36));

        users.sort((User a, User b) -> b.age - a.age);

        System.out.println(users);
    }

}

class User implements Comparable<User> {
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public int age;

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

    @Override
    public int compareTo(@NotNull User o) {
        return this.age - o.age;
    }
}

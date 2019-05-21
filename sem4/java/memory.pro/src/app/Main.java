package app;

import dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

import static security.PasswordUtils.hashPassword;

public class Main {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

        //String salt = generateSalt(512);
        //String key = hashPassword(password, salt).get();

       /* var user = new User();

        user.setFirstName("Jon");
        user.setLastName("Kotlin");
        user.setEmail("kotlin_jon@gmail.com");
        user.setUsername("Kotlin");
        user.setRegisterDate(LocalDate.now());
        user.setPasswordKey(key);
        user.setPasswordSalt(salt);

        userDao.add(user);*/

        String username = "Kotlin";
        String password = "kotlin";

        Optional<String> saltOpt = userDao.getSaltByUsername(username);

        if (saltOpt.isEmpty()) {
            System.out.println("No user found");
            return;
        }

        String salt = saltOpt.get();
        System.out.println(salt);

        String key = hashPassword(password, salt).orElseThrow(Exception::new);

        if (userDao.checkKey(username, key)) {
            System.out.println("Success");
        } else {
            System.out.println("You are a hacker");
        }


         /*String input = "asdfasdfsa";
        System.out.println(key);
        System.out.println(verifyPassword(input, key, salt) ? "Correct" : "Incorrect");*/
    }

}

package lib.Auth;

import dao.UserDao;
import schemas.User;
import security.PasswordUtils;

import javax.persistence.RollbackException;
import java.io.*;
import java.time.LocalDate;

public class Auth {
    private UserDao userDao;
    private File loginDataFile = new File("data/auth/config");

    public Auth(UserDao userDao) {
        this.userDao = userDao;

        initLoginDataFile();
    }

    public void signup(User user) throws AuthException {
        if (userDao.checkExist(user)) {
            throw new AuthException("The user already exist");
        }

        String salt = PasswordUtils.generateSalt(512);
        String key = PasswordUtils.hashPassword(user.getPassword(), salt)
                .orElseThrow(() -> new AuthException("Internal error"));

        user.setPasswordSalt(salt);
        user.setPasswordKey(key);
        user.setRegisterDate(LocalDate.now());

        try {
            userDao.add(user);
        } catch (RollbackException e) {
            throw new AuthException("Can't save the user");
        }
    }

    public void login(String username, String password) throws AuthException {
        String salt = userDao.getSaltByUsername(username)
                .orElseThrow(() -> new AuthException("Login failed"));

        String key = PasswordUtils.hashPassword(password, salt)
                .orElseThrow(() -> new AuthException("Login failed"));

        if (!checkKey(username, key)) {
            throw new AuthException("Login failed");
        }

        try {
            saveLoginData(username, key);

        } catch (IOException e) {
            throw new AuthException("Login successful, but cannot save login data");
        }
    }

    public boolean isLoggedIn() {
        try(FileInputStream fis = new FileInputStream(loginDataFile);
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            LoginData loginData = (LoginData) ois.readObject();
            return checkKey(loginData.getUsername(), loginData.getKey());

        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public boolean logout() {
        return loginDataFile.delete();
    }

    private void saveLoginData(String username, String key) throws IOException {
        LoginData loginData = new LoginData(username, key);

        try(FileOutputStream fos = new FileOutputStream(loginDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fos)
        ) {

            out.writeObject(loginData);

        }
    }

    private boolean checkKey(String username, String key) {
        return userDao.checkKey(username, key);
    }

    private void initLoginDataFile() {
        loginDataFile.getParentFile().mkdirs();
    }
}

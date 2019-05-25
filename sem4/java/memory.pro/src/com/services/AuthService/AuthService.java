package com.services.AuthService;

import dao.UserDao;
import schemas.User;
import security.PasswordUtils;

import javax.persistence.RollbackException;
import java.io.*;
import java.time.LocalDate;
import java.util.Optional;

public class AuthService {
    private String loginDataPath = "data/AuthScreen/config";

    private UserDao userDao;
    private File loginDataFile;
    private Optional<LoginData> loginDataOpt;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;

        initLoginDataFile();
        loadLoginData();
    }

    public void signup(User user) throws AuthServiceException {
        if (userDao.checkExist(user)) {
            throw new AuthServiceException("The user already exist");
        }

        String salt = PasswordUtils.generateSalt(512);
        String key = PasswordUtils.hashPassword(user.getPassword(), salt)
                .orElseThrow(() -> new AuthServiceException("Internal error"));

        user.setPasswordSalt(salt);
        user.setPasswordKey(key);
        user.setRegisterDate(LocalDate.now());

        try {
            userDao.add(user);
        } catch (RollbackException e) {
            throw new AuthServiceException("Can't save the user");
        }
    }

    public void login(String username, String password) throws AuthServiceException {
        String salt = userDao.getSaltByUsername(username)
                .orElseThrow(() -> new AuthServiceException("Login failed"));

        String key = PasswordUtils.hashPassword(password, salt)
                .orElseThrow(() -> new AuthServiceException("Login failed"));

        if (!checkKey(username, key)) {
            throw new AuthServiceException("Login failed");
        }

        setLoginData(username, key);
    }

    public void signupAndLogin(User user) throws AuthServiceException {
        signup(user);
        login(user.getUsername(), user.getPassword());

    }

    public boolean isLoggedIn() {
        if (loginDataOpt.isEmpty()) {
            return false;
        }

        LoginData loginData = loginDataOpt.get();

        boolean res = checkKey(loginData.getUsername(), loginData.getKey());

        if (!res) {
            removeLoginData();
        }

        return res;
    }

    public boolean logout() {
        return this.removeLoginData();
    }

    private boolean checkKey(String username, String key) {
        return userDao.checkKey(username, key);
    }

    private void initLoginDataFile() {
        loginDataFile = new File(loginDataPath);
        loginDataFile.getParentFile().mkdirs();
    }

    private void saveLoginData() {
        if (loginDataOpt.isEmpty()) {
            return;
        }

        try(FileOutputStream fos = new FileOutputStream(loginDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fos)
        ) {

            out.writeObject(loginDataOpt.get());

        } catch (IOException e) {
            return;
        }
    }

    private void setLoginData(String login, String key) {
        loginDataOpt = Optional.of(new LoginData(login, key));
        saveLoginData();
    }

    private boolean removeLoginData() {
        loginDataOpt = Optional.empty();
        return loginDataFile.delete();
    }

    private void loadLoginData() {
        try(FileInputStream fis = new FileInputStream(loginDataFile);
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            loginDataOpt = Optional.of((LoginData) ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            loginDataOpt = Optional.empty();
        }
    }

    public String getLoggedInUsername() {
        if (loginDataOpt.isEmpty() || !isLoggedIn()) {
            return "";
        }

        return loginDataOpt.get().getUsername();
    }

}

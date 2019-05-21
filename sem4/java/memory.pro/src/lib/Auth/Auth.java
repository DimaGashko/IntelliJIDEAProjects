package lib.Auth;

import dao.UserDao;
import schemas.User;
import security.PasswordUtils;

public class Auth {
    private UserDao userDao;
    private String keyStorePath = "data/config";

    Auth(UserDao userDao) {
        this.userDao = userDao;
    }

    public void signup(User user) throws AuthException {
        if (userDao.checkExist(user.getUsername())) {
            throw new AuthException("The user already exist");
        }

        userDao.add(user);
    }

    public void login(String username, String password) throws AuthException {
        String salt = userDao.getSaltByUsername(username)
                .orElseThrow(() -> new AuthException("Login failed"));

        String key = PasswordUtils.hashPassword(password, salt)
                .orElseThrow(() -> new AuthException("Login failed"));

        if (!checkKey(username, key)) {
            return;
        }

        saveLoginData(username, key);
    }

    public boolean isLoggedIn() {
        return true;
    }

    private void saveLoginData(String username, String key) {

    }

    private boolean checkKey(String username, String key) {
        return userDao.checkKey(username, key);
    }
}

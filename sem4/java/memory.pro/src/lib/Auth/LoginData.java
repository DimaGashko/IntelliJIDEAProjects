package lib.Auth;

import java.io.Serializable;

public class LoginData implements Serializable {

    private String username;
    private String key;

    public LoginData(String username, String key) {
        this.username = username;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

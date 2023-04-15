package core.model;

import lombok.Data;

import java.util.ResourceBundle;

import static core.util.EncoderDecoder.getDecodedValue;
import static core.util.EncoderDecoder.getEncodedValue;

@Data
public class User {
    private String login;
    private String password;

    public static User createUser() {
        return new User(getTestData("login"), getTestData("password"));
    }

    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("admin");

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }

    private User(String login, String password) {
        this.login = getEncodedValue(login);
        this.password = getEncodedValue(password);
    }

    public String getLogin() {
        return getDecodedValue(login);
    }

    public String getPassword() {
        return getDecodedValue(password);
    }
}
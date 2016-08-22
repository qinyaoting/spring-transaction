package test7.annotation;

/**
 * Created by chin on 8/22/16.
 */
public class PasswordUtils {


    @UserCase(id="47", description = "Password must contain at least one numeric")
    public boolean validatePassword(String password) {

        return (password.matches("\\w*\\d\\w*"));
    }

    @UserCase(id="48")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}

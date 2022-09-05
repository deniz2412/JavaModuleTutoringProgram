import Exceptions.InvalidEmailFormatException;
import Exceptions.InvalidPasswordFormatException;
import Exceptions.UserOtherThanTestException;

import java.util.regex.PatternSyntaxException;

public class User {

    private String username;
    private String password;
    private String email;

    //Throwing exceptions on a constructor breaks Single responsibility principle, but I kept it here for example
    public User(String username, String password, String email) throws PatternSyntaxException, InvalidPasswordFormatException, InvalidEmailFormatException, UserOtherThanTestException {

        if (username.contains("Test")) {
            this.username = username;
        } else {
            throw new UserOtherThanTestException("You tried adding a user whose name is not Test");
        }
        //2 Upper 1 special 2 digits 3 lowercase and length of 8
        if (!(password.matches("(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}"))) {
            throw new InvalidPasswordFormatException("Not a proper password");
        } else {
            this.password = password;
        }
        if (email.contains("@") && email.contains(".com")) {
            this.email = email;
        } else {
            throw new InvalidEmailFormatException("Email must contain @ and .com");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

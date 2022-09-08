import Exceptions.InvalidEmailFormatException;
import Exceptions.InvalidPasswordFormatException;
import Exceptions.InvalidUserException;
import Exceptions.UserOtherThanTestException;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;


@Getter
@Setter
public class User {
    private final Logger logger = Logger.getLogger(User.class.getName());
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private void checkEmail(String email) throws InvalidEmailFormatException {
        if (!(email.contains("@") && email.contains(".com")))
            throw new InvalidEmailFormatException("Email must contain @ and .com");
    }

    //2 Upper 1 special 2 digits 3 lowercase and length of 8
    private void checkPassword(String password) throws InvalidPasswordFormatException {
        if (!(password.matches("(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}"))) {
            throw new InvalidPasswordFormatException("Not a proper password");
        }

    }

    private void checkUsername(String username) throws UserOtherThanTestException {
        if (!username.contains("Test"))
            throw new UserOtherThanTestException("You tried adding a user whose name is not Test");

    }

    public boolean isValidUser() throws InvalidUserException {
        try {
            try {
                checkEmail(getEmail());
                checkPassword(getPassword());
                checkUsername(getUsername());
                System.out.println("User is valid");
                return true;
            } catch (InvalidPasswordFormatException | UserOtherThanTestException |
                     InvalidEmailFormatException exception) {
                throw (InvalidUserException)
                        new InvalidUserException("User is not valid").initCause(exception);
            }
        } catch (InvalidUserException invalidUserException) {
            logger.warning("User is not valid (InvalidUserException), caused by: " + invalidUserException.getCause());

        }
        return false;
    }

}

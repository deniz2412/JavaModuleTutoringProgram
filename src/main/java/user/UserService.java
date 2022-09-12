package user;

import exceptions.*;

import java.util.logging.Logger;


public class UserService {

    private final UserDaoImplInMemory userDao;
    private final Logger logger = Logger.getLogger(User.class.getName());

    public UserService(UserDaoImplInMemory userDao) {
        this.userDao = userDao;
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
        if (username.matches("^[_A-z0-9]*$")) {
            throw new UserOtherThanTestException("Username cannot have any special characters and must be atleast 6 characters long");
        }
    }

    public boolean isValidUser(User user) {
        try {
            try {
                checkEmail(user.getEmail());
                checkPassword(user.getPassword());
                checkUsername(user.getUsername());
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

    public boolean login(String username, String password) throws NoUserException {
        for (User user : userDao.getAll()) {
            if (username.equals(user.getUsername())) {
                if (isPasswordMatching(user.getPassword(), password)) {
                    User userReturn = userDao.getById(user.getId());
                    System.out.println("Logged in");
                    return true;
                } else {
                    System.out.println("Wrong Password");
                    return false;
                }
            }
        }
        throw new NoUserException("User not in List");
    }


    private boolean isPasswordMatching(String passwordOriginal, String passwordProvided) {
        return passwordOriginal.equals(passwordProvided);
    }
}

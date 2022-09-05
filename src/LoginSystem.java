import Exceptions.InvalidLoginException;
import Exceptions.NoPasswordFragmentException;
import Exceptions.NoUserException;

import java.util.ArrayList;
import java.util.List;

public class LoginSystem {

    List<User> userList = new ArrayList<>();

    public LoginSystem() {
    }

    public void addToDB(User user) {
        userList.add(user);
    }

    public void login(String username, String password) throws InvalidLoginException {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return;
            }

        }
        throw new InvalidLoginException("Invalid login, either password or username are wrong");
    }


    public String resetPassword(String username, String passwordFragment, String newPassword) throws NoUserException {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                if (user.getPassword().contains(passwordFragment)) {
                    user.setPassword(newPassword);
                    return "Password set to new password";
                } else {
                    throw new NoPasswordFragmentException("Password fragment is not matching your current password");
                }
            } else {
                throw new NoUserException("Unknown user");
            }
        }


        return "Password reset";
    }

    public void printDB() {
        for (User user : userList) {
            System.out.println(user.getUsername() + "\t" + user.getEmail());
        }

    }

    public void deleteFromDB(String username, String password) throws NoUserException {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                userList.remove(i);
            } else {
                throw new NoUserException("User not in the list");
            }
        }
    }

}


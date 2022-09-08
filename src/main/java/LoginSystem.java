
import Exceptions.NoPasswordFragmentException;
import Exceptions.NoUserException;
import Exceptions.WrongPasswordException;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class LoginSystem {

    List<User> userList = new ArrayList<>();

    private boolean userInList(User user) {
        return userList.contains(user);
    }

    public void addToList(User user) {
        if (user.isValidUser() && !userInList(user)) {
            userList.add(user);
        } else {
            System.out.println("Failed to add user to List");
        }
    }

    public User login(String username, String password) throws NoUserException {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                if (password.equals(user.getPassword())) {
                    User userReturn = userList.get(userList.indexOf(user));
                    System.out.println("Logged in");
                    return userReturn;
                } else {
                    System.out.println("Wrong Password");
                    return null;
                }
            }
        }
        throw new NoUserException("User not in List");
    }


    public void resetPassword(User user, String passwordFragment, String newPassword) throws NoPasswordFragmentException, NoUserException {
        if (userInList(user)) {
            User user1 = userList.get(userList.indexOf(user));
            if (user1.getPassword().contains(passwordFragment)) {
                user1.setPassword(newPassword);
                System.out.println("Password set to new password");
            } else {
                throw new NoPasswordFragmentException("Password fragment is not matching your current password");
            }
        } else {
            throw new NoUserException("User not in list");
        }
    }

    public void printDB() {
        for (User user : userList) {
            System.out.println(user.getUsername() + "\t" + user.getEmail());
        }

    }

    public void deleteFromList(User user, String password) throws NoUserException, WrongPasswordException {
        if (userInList(user)) {
            if (password.equals(user.getPassword())) {
                userList.remove(userList.indexOf(user));
            } else {
                throw new WrongPasswordException("Wrong Password, try again");
            }
        }
        throw new NoUserException("User not in the list");
    }


}



import exceptions.NoPasswordFragmentException;
import exceptions.NoUserException;
import exceptions.UserAlreadyinListException;
import exceptions.WrongPasswordException;
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
            throw new UserAlreadyinListException("User is already in list");
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


    public String resetPassword(User user, String passwordFragment, String newPassword) throws NoPasswordFragmentException, NoUserException {
        if (userInList(user)) {
            User user1 = userList.get(userList.indexOf(user));
            if (user1.getPassword().contains(passwordFragment)) {
                user1.setPassword(newPassword);
                return "Password set to new password";
            } else {
                throw new NoPasswordFragmentException("Password fragment is not matching your current password");
            }
        } else {
            throw new NoUserException("User not in list");
        }
    }

    public void deleteFromList(User user, String password) throws NoUserException, WrongPasswordException {
        if (userInList(user)) {
            if (password.equals(user.getPassword())) {
                userList.remove(user);
                return;
            } else {
                throw new WrongPasswordException("Wrong Password, try again");
            }
        }
        throw new NoUserException("User not in the list");
    }
    }





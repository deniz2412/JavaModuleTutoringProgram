package Exceptions;

import java.util.ArrayList;
import java.util.List;

public class LoginSystem {

    List<User> userList = new ArrayList<>();

    public LoginSystem() {
    }

    public void addToDB(User user) {
        userList.add(user);
    }

    public String login(String username, String password) throws InvalidLoginException {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return "Login is good";
            }

        }
        throw new InvalidLoginException("Invalid login, either password or username are wrong");
    }


    public String resetPassword(String username, String passwordFragment, String newPassword) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername() == username) {
                if (userList.get(i).getPassword().contains(passwordFragment)) {
                    userList.get(i).setPassword(newPassword);
                    return "Password set to new password";
                } else
                    return "Password fragment is not matching your current password";
            } else {
                return "Unknown user";
            }
        }
        return null;
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
                throw new NoUserException("Exceptions.User not in the list");
            }
        }
    }

}


import Exceptions.NoPasswordFragmentException;
import Exceptions.NoUserException;
import Exceptions.WrongPasswordException;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        LoginSystem loginSystem = new LoginSystem();
        Logger logger = Logger.getLogger(Main.class.getName());
        User userGood = new User("Test", "PSsrw12@", "Test@Test.com");
        User userInvalidPW = new User("Test", "P@", "Test@Test.com");
        User userInvalidEmail = new User("Test", "PSsrw12@", "Tester");
        User userInvalidUser = new User("Zed", "PSsrw12@", "Test@Test.com");


        userInvalidPW.isValidUser();
        userInvalidEmail.isValidUser();
        userInvalidUser.isValidUser();
        loginSystem.userList.add(userInvalidUser);
        System.out.println(loginSystem.userList.size());

        loginSystem.userList.add(userGood);

        try {
            loginSystem.login(userGood.getUsername(), userGood.getPassword()); //In list
            //loginSystem.login(userInvalidPW.getUsername(),userInvalidPW.getPassword()); //Not in List
        } catch (NoUserException exception) {
            logger.warning(exception.getMessage());
        }

        try {
            loginSystem.resetPassword(userGood, "PSsr", "PSsrw13@"); //Good fragment
            loginSystem.resetPassword(userGood, "Z", "PSsr13@"); //Bad fragment
        } catch (NoUserException | NoPasswordFragmentException exception) {
            logger.warning(exception.getMessage());
        }

        try {
            loginSystem.deleteFromList(userGood, "PSsrw12@"); //in List
            loginSystem.deleteFromList(userGood, "PSsr13@"); //in List wrong password
            loginSystem.deleteFromList(userInvalidEmail, "PSsr13@"); // not in list
        } catch (NoUserException | WrongPasswordException exception) {
            logger.warning(exception.getMessage());
            logger.info(loginSystem.userList.toString());
        }


    }
}

//Method overriding exceptions
        /* If the parent class does not declare an exception in the method the child override cant declare a checked exception, but it can do an unchecked one
        Works if both parent and child declare the same exception;
         */

//Multiple exceptions in one catch
        /* Old way of doing it is putting multiple catch blocks one after another
        New way is using a multicatch block ex. catch(Exception1 e1| Exception2 e2| ExceptionN en){...}
         */

//Not sure if this what we were supposed to do when you said not to use instance of, I'd love some feedback on this please

/*
Questions in Topics part
    Exceptions
1.	What they are?
    It's an event of some sort that disrupts the flow of the program it is implemented as an object which is thrown at runtime
2.	What kinds of exceptions exist?
    We have checked and unchecked exceptions
    -Checked exceptions:
        These types of exceptions get checked at compile time and wont let you continue if its an exception, they cant be ignored
    -Unchecked:
        They get checked at runtime which means they may or may not even happen.
3.	How are they handled?
    Using exception handlers;
    Whenever there is an exception inside a method, the method creates an Exception Object and it gets handed to JVM
    The object contains the name and description of exception and the current state of the program
4.	Reserved words: try-catch, throw and throws
    try-catch: try part is used to specify a code block where the exception should take place(must have catch or finally as a followup)
    throw: With this keyword we can throw an exception
    throws: It's used in declaring exceptions. It pretty much says that there may be an exception thrown in the method
    public int division(int a, int b) throws ArithmeticException{...}
 */
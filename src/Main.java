import Exceptions.InvalidEmailFormatException;
import Exceptions.InvalidLoginException;
import Exceptions.InvalidPasswordFormatException;
import Exceptions.UserOtherThanTestException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        //Example of a chained exception where the root cause for InvalidPasswordFormat is PatternSyntaxException
        try {
            InvalidLoginException invalidLoginException = new InvalidLoginException("First exception we get thrown");
            invalidLoginException.initCause(new InvalidPasswordFormatException("Actual reason it failed"));
            throw invalidLoginException;
        } catch (InvalidLoginException exception) {
            System.out.println(exception);
            System.out.println(exception.getCause());


        }
        System.out.println();
        //Throwing exceptions on a constructor breaks Single responsibility principle, but I kept it here for example
        for (int i = 0; i < 10; i++) {
            User user = new User(String.format("Test%d", i), "PSsrw12@", String.format("Test%d@test.com", i));
            loginSystem.addToDB(user);
        }

        //RuntimeException gets thrown if user for example during the login enters creds that are not in the system
        try {
            loginSystem.login("WillThrow", "password123");

        } catch (InvalidLoginException ex) {
            System.out.println(ex);

        }

        //Method overriding exceptions
        /* If the parent class does not declare an exception in the method the child override cant declare a checked exception, but it can do an unchecked one
        Works if both parent and child declare the same exception;
         */


        //Implement logger for next task
        Logger logger = Logger.getLogger(Main.class.getName());
        //Multiple exceptions in one catch
        /* Old way of doing it is putting multiple catch blocks one after another
        New way is using a multicatch block ex. catch(Exception1 e1| Exception2 e2| ExceptionN en){...}
         */
        try {
            User userUsernameBad = new User("Test", "PSsrw12@", "Test90test");
            //Both the username and the email are invalid which means we can get 2 exceptions
        } catch (InvalidEmailFormatException | UserOtherThanTestException exception) {
            if (exception.equals(InvalidEmailFormatException.class))
                logger.log(Level.WARNING, "exception found and its InvalidEmailFormat", exception);
            else
                logger.log(Level.WARNING, "exception found and its UserOtherThanTestException", exception);
        }
        //Not sure if this what we were supposed to do when you said not to use instance of, I'd love some feedback on this please


    }
}

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
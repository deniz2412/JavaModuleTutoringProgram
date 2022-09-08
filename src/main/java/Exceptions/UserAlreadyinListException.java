package Exceptions;

public class UserAlreadyinListException extends RuntimeException {

    public UserAlreadyinListException(String str) {
        super(str);
    }
}

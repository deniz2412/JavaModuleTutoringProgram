package exceptions;

public class UserAlreadyinListException extends RuntimeException {

    public UserAlreadyinListException(String str) {
        super(str);
    }
}

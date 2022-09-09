package exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String str) {
        super(str);
    }
}

package exceptions;

public class NoUserException extends RuntimeException {
    public NoUserException(String str) {
        super(str);
    }
}

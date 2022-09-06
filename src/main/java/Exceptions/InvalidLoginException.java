package Exceptions;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String str) {
        super(str);
    }
}

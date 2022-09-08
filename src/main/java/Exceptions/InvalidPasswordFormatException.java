package Exceptions;

public class InvalidPasswordFormatException extends RuntimeException {

    public InvalidPasswordFormatException(String str) {
        super(str);
    }
}

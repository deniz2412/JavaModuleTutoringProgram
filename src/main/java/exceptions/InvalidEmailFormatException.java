package exceptions;

public class InvalidEmailFormatException extends RuntimeException {

    public InvalidEmailFormatException(String str) {
        super(str);
    }
}
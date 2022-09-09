package exceptions;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String str) {
        super(str);
    }

}

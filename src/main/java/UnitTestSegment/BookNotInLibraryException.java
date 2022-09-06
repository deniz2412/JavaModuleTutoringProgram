package UnitTestSegment;

public class BookNotInLibraryException extends RuntimeException {
    public BookNotInLibraryException(String str) {
        super(str);
    }
}

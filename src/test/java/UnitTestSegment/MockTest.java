package UnitTestSegment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class MockTest {

    //I hope this is what was meant by doing a mock test. Mock testing becomes would be done easier if the app was bigger and more complex
    Library library = new Library();


    @Test
    void checkForDamageOverallTestMock() {
        //Preparation
        Book theTrial = Mockito.mock(Book.class);
        assertThrows(BookNotInLibraryException.class, () -> {
            //Send a book that's not in the list
            library.checkForDamage(theTrial);
        });

    }
}

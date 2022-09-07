package UnitTestSegment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library;

    static LogManager logManager = LogManager.getLogManager();
    static Logger log = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @BeforeAll
    static void testStart() {
        log.log(Level.INFO, "Test suite is starting");
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                Arguments.of(5, 5),
                Arguments.of(15, 5),
                Arguments.of(25, 15),
                Arguments.of(45, 25),
                Arguments.of(110, 505));

    }

    @AfterAll
    static void testAfter() {
        log.log(Level.INFO, "Test suite has finished");
    }

    @BeforeEach
    void setUp() {
        log.log(Level.INFO, "Test is starting");
        library = new Library();

    }

    @Test
    void checkForDamageOverallTest() {
        //Preparation
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);
        Book theTrial = new Book("The Trial", "Franz Kafka", 10, 1925, "Novel/Dystopian fiction", "9780805210408", false);
        library.listofBooks.add(divineComendy);
        int divineIndex = library.listofBooks.indexOf(divineComendy);
        assertAll("Test",
                () -> assertThrows(BookNotInLibraryException.class, () -> {
                    //Send a book that's not in the list
                    library.checkForDamage(theTrial);
                }),
                () -> assertThrows(InvalidBookStatusException.class, () -> {
                    library.listofBooks.get(divineIndex).setDamagePercentage(-5);
                    library.checkForDamage(library.listofBooks.get(divineIndex));
                }),
                () -> assertThrows(InvalidBookStatusException.class, () -> {
                    library.checkForDamage(library.listofBooks.get(divineIndex));
                }),
                () -> assertThrows(NullPointerException.class, () -> {
                    //Don't set a damage status which makes it null by default
                    library.listofBooks.get(divineIndex).setDamagePercentage(null);
                    library.checkForDamage(library.listofBooks.get(divineIndex));
                }),

                () -> {
                    library.listofBooks.get(0).setDamagePercentage(10);
                    assertEquals("Printing press new", library.checkForDamage(divineComendy));
                },
                () -> {
                    library.listofBooks.get(0).setDamagePercentage(15);
                    assertEquals("Minimal wear", library.checkForDamage(divineComendy));
                },
                () -> {
                    library.listofBooks.get(0).setDamagePercentage(25);
                    assertEquals("Read outside in the rain", library.checkForDamage(divineComendy));
                },
                () -> {
                    library.listofBooks.get(0).setDamagePercentage(45);
                    assertEquals("Well read, needs to be restored", library.checkForDamage(divineComendy));
                },
                () -> {
                    library.listofBooks.get(0).setDamagePercentage(85);
                    assertEquals("Read in battle, needs to be restored", library.checkForDamage(divineComendy));
                },
                () -> {
                    library.listofBooks.get(0).setDamagePercentage(100);
                    assertEquals("You destroyed the book what are you even returning", library.checkForDamage(divineComendy));
                    //Check if book is still in list
                    assertFalse(library.listofBooks.contains(divineComendy));
                }

        );
    }

    @Test()
    void returnBookOverallTest() {
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);
        Book theTrial = new Book("The Trial", "Franz Kafka", 10, 1925, "Novel/Dystopian fiction", "9780805210408", false, false, 5, 90);
        library.listofBooks.add(divineComendy);
        int divineIndex = library.listofBooks.indexOf(divineComendy);
        //Price for book is 5
        assertAll("Return Book testing",
                () -> assertThrows(BookNotInLibraryException.class, () -> {
                    //Send a book that's not in the list
                    library.returnBook(theTrial);
                }),
                () -> assertThrows(NullPointerException.class, () -> {
                    library.listofBooks.get(divineIndex).setDaysLent(null);
                    library.returnBook(library.listofBooks.get(divineIndex));
                }),
                () -> assertThrows(InvalidBookStatusException.class, () -> {
                    library.listofBooks.get(divineIndex).setDaysLent(-2);
                    library.returnBook(library.listofBooks.get(divineIndex));
                }),

                () -> {
                    library.listofBooks.get(0).setDaysLent(5);
                    assertEquals(5, library.returnBook(library.listofBooks.get(divineIndex)));
                },
                () -> {
                    library.listofBooks.get(0).setDaysLent(15);
                    assertEquals(5, library.returnBook(library.listofBooks.get(divineIndex)));
                },
                () -> {
                    library.listofBooks.get(0).setDaysLent(25);
                    assertEquals(15, library.returnBook(library.listofBooks.get(divineIndex)));
                },
                () -> {
                    library.listofBooks.get(0).setDaysLent(45);
                    assertEquals(25, library.returnBook(library.listofBooks.get(divineIndex)));
                },
                () -> {
                    library.listofBooks.get(0).setDaysLent(110);
                    assertEquals(505, library.returnBook(library.listofBooks.get(divineIndex)));
                }

        );

    }

    @ParameterizedTest
    @MethodSource("provideValues")
    void returnBookTestParameterized(int daysLent, int value) {
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);
        library.listofBooks.add(divineComendy);
        int divineIndex = library.listofBooks.indexOf(divineComendy);
        library.listofBooks.get(0).setDaysLent(daysLent);
        assertEquals(value, library.returnBook(library.listofBooks.get(0)));
    }

    @Test
    void checkIfBookIsSame() {
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);
        library.listofBooks.add(divineComendy);
        assertEquals(divineComendy, library.listofBooks.get(0));
    }

    @Test
    void bookToStringTest() {
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);

        assertEquals("Book{" +
                "name='" + "Divine Comedy" + '\'' +
                ", author='" + "Dante Alighieri" + '\'' +
                ", lendingFee=" + 5 +
                ", releaseYear=" + 1321 +
                ", genre='" + "Epic poetry" + '\'' +
                ", ISBN='" + "9780394309071" + '\'' +
                ", borrowed=" + "false" +

                '}', divineComendy.toString());
    }

    @Test
    void bookTest() {
        Book theTrial = new Book("The Trial", "Franz Kafka", 10, 1925, "Novel/Dystopian fiction", "9780805210408", false, true, 5, 50);
        assertAll(
                () -> assertEquals("The Trial", theTrial.getName()),
                () -> assertEquals("Franz Kafka", theTrial.getAuthor()),
                () -> assertEquals(10, theTrial.getLendingFee()),
                () -> assertEquals(1925, theTrial.getReleaseYear()),
                () -> assertEquals("9780805210408", theTrial.getISBN()),
                () -> assertFalse(theTrial.isBorrowed()),
                () -> assertTrue(theTrial.isNeedsRestoration()),
                () -> assertEquals(5, theTrial.getDaysLent()),
                () -> assertEquals(50, theTrial.getDamagePercentage()),
                () -> assertEquals("Novel/Dystopian fiction", theTrial.getGenre())
        );

    }

    @AfterEach
    void tearDown() {
        log.log(Level.INFO, "Test has finished");
    }

}
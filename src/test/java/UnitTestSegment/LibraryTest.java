package UnitTestSegment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library;

    @BeforeEach
    void setUp() {
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
                    //Send a book thats not in the list
                    library.checkForDamage(theTrial);
                }),
                () -> assertThrows(InvalidBookStatusException.class, () -> {
                    library.listofBooks.get(divineIndex).setDamagePercentage(-5);
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

    @Test
    void returnBookOverallTest() {
        Book divineComendy = new Book("Divine Comedy", "Dante Alighieri", 5, 1321, "Epic poetry", "9780394309071", false);
        Book theTrial = new Book("The Trial", "Franz Kafka", 10, 1925, "Novel/Dystopian fiction", "9780805210408", false);
        library.listofBooks.add(divineComendy);
        int divineIndex = library.listofBooks.indexOf(divineComendy);
        //Price for book is 5
        assertAll("Return Book testing",
                () -> assertThrows(BookNotInLibraryException.class, () -> {
                    //Send a book that's not in the list
                    library.returnBook(theTrial);
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


    @AfterEach
    void tearDown() {
    }


}
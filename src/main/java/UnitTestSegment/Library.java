package UnitTestSegment;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Library {
    public List<Book> listofBooks = new ArrayList<>();
    public double damageFeeCoeff = 1;

    public String checkForDamage(Book book) {
        Integer damageStatus;
        if (listofBooks.contains(book)) {
            damageStatus = book.getDamagePercentage();
            if (damageStatus < 0 || damageStatus >= 101)
                throw new InvalidBookStatusException("You cant provide a negative damage status/ damage status above 100");
            else if (damageStatus >= 0 && damageStatus <= 10)
                return "Printing press new";
            else if (damageStatus > 10 && damageStatus <= 20)
                return "Minimal wear";
            else if (damageStatus > 20 && damageStatus <= 40) {
                damageFeeCoeff += 0.1;
                return "Read outside in the rain";
            } else if (damageStatus > 40 && damageStatus <= 80) {
                damageFeeCoeff += 0.3;
                book.setNeedsRestoration(true);
                return "Well read, needs to be restored";
            } else if (damageStatus > 80 && damageStatus < 100) {
                damageFeeCoeff += 0.7;
                book.setNeedsRestoration(true);
                return "Read in battle, needs to be restored";
            } else if (damageStatus == 100) {
                listofBooks.remove(book);
                return "You destroyed the book what are you even returning";
            }
        }
        throw new BookNotInLibraryException("You have the wrong library");
    }

    public double returnBook(Book book) {
        if (listofBooks.contains(book)) {
            float leasePrice = book.getLendingFee();
            Integer daysLeased = book.getDaysLent();
            book.setBorrowed(false);
            if (daysLeased < 0)
                throw new InvalidBookStatusException("You cant provide negative days leased");
            else if (daysLeased <= 10)
                return leasePrice * damageFeeCoeff;
            else if (daysLeased > 10 && daysLeased <= 20)
                return leasePrice * damageFeeCoeff;
            else if (daysLeased > 20 && daysLeased <= 40) {
                System.out.println("You are late, fixed fee is applied");
                return leasePrice * damageFeeCoeff + 10;
            } else if (daysLeased > 40 && daysLeased <= 100) {
                System.out.println("You are late, fixed fee is applied");
                return leasePrice * damageFeeCoeff + 20;
            } else if (daysLeased > 100) {
                System.out.println("You are more than 100 days late why bother returning it");
                return leasePrice * damageFeeCoeff + 100 * leasePrice;
            }
        }
        throw new BookNotInLibraryException("You have the wrong library");
    }
}


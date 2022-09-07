package UnitTestSegment;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Book {
    private String name;
    private String author;
    private Integer lendingFee;
    private Integer releaseYear;
    private String genre;
    private String ISBN;
    private boolean borrowed;
    private boolean needsRestoration;
    private Integer daysLent;
    private Integer damagePercentage;

    public Book(String name, String author, int lendingFee, int releaseYear, String genre, String ISBN, boolean sborrowed) {
        this.name = name;
        this.author = author;
        this.lendingFee = lendingFee;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.ISBN = ISBN;
        this.borrowed = sborrowed;
        damagePercentage = null;
    }

    public Book(String name, String author, Integer lendingFee, Integer releaseYear, String genre, String ISBN, boolean borrowed, boolean needsRestoration, Integer daysLent, Integer damagePercentage) {
        this.name = name;
        this.author = author;
        this.lendingFee = lendingFee;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.ISBN = ISBN;
        this.borrowed = borrowed;
        this.needsRestoration = needsRestoration;
        this.daysLent = daysLent;
        this.damagePercentage = damagePercentage;
    }

    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", lendingFee=" + lendingFee +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", borrowed=" + borrowed +
                '}';
    }
}
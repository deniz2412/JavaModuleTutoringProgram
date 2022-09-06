package UnitTestSegment;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLendingFee() {
        return lendingFee;
    }

    public void setLendingFee(Integer lendingFee) {
        this.lendingFee = lendingFee;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isNeedsRestoration() {
        return needsRestoration;
    }

    public void setNeedsRestoration(boolean needsRestoration) {
        this.needsRestoration = needsRestoration;
    }

    public int getDaysLent() {
        return daysLent;
    }

    public void setDaysLent(Integer daysLent) {
        this.daysLent = daysLent;
    }

    public int getDamagePercentage() {
        return damagePercentage;
    }

    public void setDamagePercentage(int damagePercentage) {
        this.damagePercentage = damagePercentage;
    }
}

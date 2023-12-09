package guimasi.entities;

import java.util.Random;

public abstract class PaperElement {
    private long ISBNCode;
    private final String title;
    private final int year;
    private  int numberOfPages;

    public PaperElement( String title, int year) {
        Random random = new Random();
        this.ISBNCode = random.nextInt(0,200000);
        this.title = title;
        this.year = year;
        this.numberOfPages = random.nextInt(0,500);
    }

    // GETTER

    public long getISBNCode() {
        return ISBNCode;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    // SETTER

    public void setISBNCode(long ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    //TO STRING

    @Override
    public String toString() {
        return "PaperElement{" +
                "ISBNCode=" + ISBNCode +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

}

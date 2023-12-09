package guimasi.entities;


public class Magazine extends PaperElement {

    private final Periodicity periodicity;

    public Magazine(String title, int year, Periodicity periodicity) {
        super(title, year);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "ISBNCode=" + this.getISBNCode() +
                ", title='" + this.getTitle() + '\'' +
                ", year=" + this.getYear() +
                ", numberOfPages=" + this.getNumberOfPages() +
                ", periodicity=" + periodicity +
                '}';
    }
}

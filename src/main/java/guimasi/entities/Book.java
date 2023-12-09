package guimasi.entities;

public class Book extends PaperElement{
    private final String author;
    private final String genre;

    public Book(String title, int year, String author, String genre) {
        super(title, year);
        this.author = author;
        this.genre = genre;
    }


    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }



    @Override
    public String toString() {
        return "Book{" +
                "ISBNCode=" + this.getISBNCode() +
                ", title='" + this.getTitle() + '\'' +
                ", year=" + this.getYear() +
                ", numberOfPages=" + this.getNumberOfPages() +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

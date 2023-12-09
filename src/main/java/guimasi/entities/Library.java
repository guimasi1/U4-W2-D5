package guimasi.entities;


import org.apache.commons.io.FileUtils;

import java.awt.print.Paper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<PaperElement> Library;

    public Library(List<PaperElement> library) {
        this.Library = library;
    }

    public List<PaperElement> getLibrary() {
        return Library;
    }

    public void addElement(PaperElement element) {
        this.Library.add(element);
    }

    public void removeElement(long isbn) {
        this.Library = Library.stream().filter(element -> (element.getISBNCode() == isbn)).toList();

    }

    public void addListOfElements(List<PaperElement> list) {
        this.Library.addAll(list);
    }

    public void searchByISBN(long isbn) {
        if (this.Library.stream().anyMatch(element -> element.getISBNCode() == isbn)) {
            List<PaperElement> elementToShow = this.Library.stream().filter(element -> element.getISBNCode() == isbn).toList();
            System.out.println(elementToShow);
        } else {
            System.out.println("This library doesn't contain the given isbn number: " + isbn + ".");
        }
    }

    public void searchByYear(int year) {
        if (this.Library.stream().anyMatch(element -> element.getYear() == year)) {
            List<PaperElement> elementToShow = this.Library.stream().filter(element -> element.getYear() == year).toList();
            System.out.println(elementToShow);
        } else {
            System.out.println("This library doesn't contain elements published in " + year + ".");
        }
    }

    public void searchByAuthor(String author) {

        if(this.Library.stream().anyMatch(element -> element instanceof Book)) {
            List<PaperElement> books = this.Library.stream().filter(element -> element instanceof Book).toList();
            List<PaperElement> booksByAuthor = books.stream().filter(book -> Objects.equals(((Book) book).getAuthor(), author)).toList();
            System.out.println(booksByAuthor);
        }
    }

    public void saveOnDisc() {
        File booksFile = new File("src/book_archive.txt");
        File magazinesFile = new File("src/magazine_archive.txt");
        List<PaperElement> books = this.Library.stream().filter(element -> element instanceof Book).toList();
        List<PaperElement> magazines = this.Library.stream().filter(element -> element instanceof Magazine).toList();
        for (int i = 0; i < books.size(); i++) {
            try{
                FileUtils.writeStringToFile(booksFile, ((Book)books.get(i)).getISBNCode() + "@" + ((Book)books.get(i)).getTitle() + "@"
                        +((Book)books.get(i)).getYear() + "@" + ((Book)books.get(i)).getNumberOfPages() + "@"
                        +((Book)books.get(i)).getGenre() + "@" +((Book)books.get(i)).getAuthor() + "@" , StandardCharsets.UTF_8, true);
            } catch(IOException e) {
                System.out.println("errore" + e.getMessage());
            }
        }
        for (int i = 0; i < magazines.size(); i++) {
            try{

                FileUtils.writeStringToFile(magazinesFile, ((Magazine)magazines.get(i)).getISBNCode() + "@"
                        + ((Magazine) magazines.get(i)).getTitle() + "@" + ((Magazine) magazines.get(i)).getYear() + "@"
                        + ((Magazine) magazines.get(i)).getNumberOfPages() + "@" + ((Magazine) magazines.get(i)).getPeriodicity() + "@", StandardCharsets.UTF_8, true);
            } catch(IOException e) {
                System.out.println("errore" + e.getMessage());
            }
        }

    }

    public void readFile() {
        try {
            File booksFile = new File("src/book_archive.txt");
            File magazinesFile = new File("src/magazine_archive.txt");
            String contenuto1 = FileUtils.readFileToString(booksFile , StandardCharsets.UTF_8);
            String contenuto2 = FileUtils.readFileToString(magazinesFile, StandardCharsets.UTF_8);
            System.out.println("Contenuto del file dei libri: " + contenuto1);
            System.out.println("Contenuto del file delle riviste: " + contenuto2);
        } catch (IOException e) {
            System.out.println("errore" + e.getMessage());
        }
    }

    public void loadInLibrary() {
        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();

        try {
            File booksFile = new File("src/book_archive.txt");
            File magazinesFile = new File("src/magazine_archive.txt");
            String contenuto1 = FileUtils.readFileToString(booksFile , StandardCharsets.UTF_8);
            String contenuto2 = FileUtils.readFileToString(magazinesFile, StandardCharsets.UTF_8);
            String[] stringsBooks = contenuto1.split("@");
            String[] stringsMagazines = contenuto2.split("@");
            for (int i = 0; i < stringsBooks.length; i += 6) {
                long isbn = Long.parseLong(stringsBooks[i]);
                String title = stringsBooks[i + 1];
                int year = Integer.parseInt(stringsBooks[i + 2]);
                int numberOfPages = Integer.parseInt(stringsBooks[i + 3]);
                String genre = stringsBooks[i + 4];
                String author = stringsBooks[i + 5];
                Book book = new Book(title,year,author,genre);
                book.setNumberOfPages(numberOfPages);
                book.setISBNCode(isbn);
                books.add(book);

            }
            for (int i = 0; i < stringsMagazines.length; i += 5) {
                long isbn = Long.parseLong(stringsMagazines[i]);
                String title = stringsMagazines[i + 1];
                int year = Integer.parseInt(stringsMagazines[i + 2]);
                int numberOfPages = Integer.parseInt(stringsMagazines[i + 3]);
                Periodicity periodicity = Periodicity.valueOf(stringsMagazines[i + 4]);
                Magazine magazine = new Magazine(title, year, periodicity);
                magazine.setNumberOfPages(numberOfPages);
                magazine.setISBNCode(isbn);
                magazines.add(magazine);
            }
        } catch (IOException e) {
            System.out.println("errore" + e.getMessage());
        }
        System.out.println("libri che sono sul file book_archive.txt: " + books);
        System.out.println("Riviste che sono sul file magazine_archive.txt: " + magazines);
    }

    public Map<Integer, List<PaperElement>> collectByYear () {
        return this.Library.stream().collect(Collectors.groupingBy(PaperElement::getYear));
    }

    public Map<String, List<PaperElement>> collectByGenre () {
        return this.Library.stream().filter(element -> element instanceof Book)
                .collect(Collectors.groupingBy( book -> ((Book) book).getGenre()));
    }

    public Map<Periodicity, List<PaperElement>> collectByPeriodicity () {
        return this.Library.stream().filter(element -> element instanceof Magazine)
                .collect(Collectors.groupingBy( magazine -> ((Magazine) magazine).getPeriodicity()));
    }

    public List<PaperElement> sortByOldestElement () {
        System.out.println("Ordinati dal più vecchio al più recente:");
        return this.Library.stream().sorted(Comparator.comparing(PaperElement::getYear)).toList();
    }

    public List<PaperElement> sortByMoreRecentElement () {
        System.out.println("Ordinati dal più recente al più vecchio:");
        return this.Library.stream().sorted(Comparator.comparing(PaperElement::getYear).reversed()).toList();
    }

    public List<PaperElement> sortByAlphabeticalOrder () {
        System.out.println("Ordinati in ordine alfabetico:");
        return this.Library.stream().sorted(Comparator.comparing(PaperElement::getTitle)).toList();
    }

    public int totalPages () {
        System.out.println("Totale delle pagine di tutti gli elementi:");
        return this.Library.stream().mapToInt(PaperElement::getNumberOfPages).sum();

    }



    @Override
    public String toString() {
        return "Library{" +
                "Library=" + Library +
                '}';
    }
}

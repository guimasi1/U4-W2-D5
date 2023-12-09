package guimasi;

import com.github.javafaker.Faker;
import guimasi.entities.*;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Application {

    public static void main(String[] args) {

        Faker faker = new Faker();
        Random random = new Random();

        List<PaperElement> books = new ArrayList<>();
        List<PaperElement> magazines = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int randomYear = random.nextInt(1930, 2024);
            Book book = new Book(faker.book().title(), randomYear, faker.book().author(), faker.book().genre());
            books.add(book);
            int randomYear2 = random.nextInt(1980, 2024);
            Periodicity periodicity1 = Periodicity.WEEKLY;
            Periodicity periodicity2 = Periodicity.MONTHLY;
            Periodicity periodicity3 = Periodicity.HALFYEARLY;
            int number = random.nextInt(1, 4);
            Periodicity periodicity = Periodicity.WEEKLY;
            if (number == 1) periodicity = periodicity1;
            if (number == 2) periodicity = periodicity2;
            if (number == 3) periodicity = periodicity3;

            Magazine magazine = new Magazine(faker.educator().course(), randomYear, periodicity);
            magazines.add(magazine);
        }
        Book book = new Book("prova", 2022, "Geronimo Stilton", "Fiction");
        Book book1 = new Book("Il forno scomparso", 2022, "Geronimo Stilton", "Fiction");
        Book book2 = new Book("Il forno", 2022, "Geronimo Stilton", "Fiction");
        Book book3 = new Book("Il forno2", 2021, "Geronimo Stilton", "Fiction");
        Book book4 = new Book("Il forno3", 2021, "Geronimo Stilton", "Fiction");
        books.add(book);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        Library newLibrary = new Library(books);

        newLibrary.addListOfElements(magazines);
        System.out.println(newLibrary.toString());

        long toShowISBN = newLibrary.getLibrary().get(1).getISBNCode();

        System.out.println(toShowISBN);
        newLibrary.searchByISBN(toShowISBN);
        newLibrary.searchByISBN(123);

        int toShowYear = newLibrary.getLibrary().get(1).getYear();

        System.out.println(toShowYear);
        newLibrary.searchByYear(toShowYear);
        newLibrary.searchByYear(2021);

        newLibrary.addElement(book);

        newLibrary.searchByAuthor("bello");

        // newLibrary.saveOnDisc();
        // newLibrary.readFile();
        /*newLibrary.loadInLibrary();*/

        Map<Integer, List<PaperElement>> elementsByYear = newLibrary.collectByYear();
        System.out.println(elementsByYear);
        Map<String, List<PaperElement>> booksByGenre = newLibrary.collectByGenre();
        System.out.println(booksByGenre);
        Map<Periodicity, List<PaperElement>> magazinezByGenre = newLibrary.collectByPeriodicity();
        System.out.println(magazinezByGenre);
        System.out.println(newLibrary.sortByOldestElement());
        System.out.println(newLibrary.sortByMoreRecentElement());
        System.out.println(newLibrary.sortByAlphabeticalOrder());
        System.out.println(newLibrary.totalPages());
        newLibrary.searchByTitle("the");
        System.out.println(newLibrary.orderByNumberOfPages());
        System.out.println(newLibrary.collectByAuthor());

    }
}




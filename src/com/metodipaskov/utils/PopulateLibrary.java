package com.metodipaskov.utils;

import com.metodipaskov.entities.Book;
import com.metodipaskov.entities.HoldRequest;
import com.metodipaskov.entities.Loan;
import com.metodipaskov.entities.actors.*;
import com.metodipaskov.services.BookManagementService;
import com.metodipaskov.services.HoldRequestManagementService;
import com.metodipaskov.services.LoanManagementService;
import com.metodipaskov.services.UserManagementService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PopulateLibrary {

    private static List<Loan> loans = new ArrayList<>();
    private static List<Person> users = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<HoldRequest> holdRequests = new ArrayList<>();
    private static UserManagementService userService = UserManagementService.getInstance();
    private static BookManagementService bookService = BookManagementService.getInstance();
    private static HoldRequestManagementService holdReqService = HoldRequestManagementService.getInstance();
    private static LoanManagementService loanService = LoanManagementService.getInstance();
    private static final String resourcesPath = ".\\src\\resources\\";


    public static void populate() {
        collectBooks();
        collectUsers();
        collectLoans();
        collectHoldReq();
    }

    private static void collectLoans() {
        if (users.isEmpty()) {
            users = userService.getUsers();
        }
        if (books.isEmpty()) {
            books = bookService.getAllBooksInLibrary();
        }

        String fileCompletedLoans = resourcesPath + "CompletedLoans.txt";
        String fileNotCompletedLoans = resourcesPath + "NotCompletedLoans.txt";

        collectLoansFromSourceFile(fileCompletedLoans, true);
        collectLoansFromSourceFile(fileNotCompletedLoans, false);

        loanService.setLoans(loans);
    }

    private static void collectLoansFromSourceFile(String filepath, boolean completedLoans) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath));
             Scanner scanner = new Scanner(reader)) {

            scanner.useDelimiter("\\t");
            while (scanner.hasNextLine()) {

                int borrowerId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                int bookId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                int issuerId = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                LocalDate dateIssued;
                Integer receiverId = null;
                LocalDate dateReturned = null;
                boolean finePaid = true;
                boolean isIssued = false;
                Person receiver;

                if (completedLoans) {
                    dateIssued = LocalDate.parse(scanner.next());
                    scanner.skip(scanner.delimiter());
                    receiverId = scanner.nextInt();
                    scanner.skip(scanner.delimiter());
                    dateReturned = LocalDate.parse(scanner.nextLine());

                } else {
                    dateIssued = LocalDate.parse(scanner.nextLine());
                    finePaid = false;
                    isIssued = true;
                }

                Person borrower = getUser(borrowerId);
                Person issuer = getUser(issuerId);
                Book book = getBook(bookId);

                Loan loan = new Loan((Borrower) borrower, book, (Staff) issuer);
                loan.setIssueDate(dateIssued);
                loan.setFinePaid(finePaid);
                book.setIssued(isIssued);

                if (completedLoans) {
                    receiver = getUser(receiverId);

                    loan.setReceiver((Staff) receiver);
                    loan.setReturnDate(dateReturned);
                }

                ((Borrower) borrower).addLoan(loan);
                loans.add(loan);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectBooks() {
        books = new ArrayList<>(Arrays.asList(
                new Book("Children of the Corn V: Fields of Terror", "Gabriela Mistral", "development"),
                new Book("Amandla! A Revolution in Four Part Harmony", "Stephen King", "mystery"),
                new Book("Commune, La (Paris, 1871)", "Honore de Balzac", "mystery"),
                new Book("Dr. Jekyll and Ms. Hyde", "Ernest Hemingway", "science fiction"),
                new Book("Woochi: The Demon Slayer", "Graham Greene", "classics"),
                new Book("66 Scenes From America", "Thomas Mann", "romance"),
                new Book("100 Men and a Girl (One Hundred Men and a Girl)", "Douglas Adams", "science fiction"),
                new Book("Thirty-Five Something (Tout pour plaire)", "William Blake", "art"),
                new Book("Life or Something Like It", "Ernest Hemingway", "thriller"),
                new Book("On Dangerous Ground", "Toni Morrison", "memoir"),
                new Book("Proof of Life", "Henry Miller", "adventure"),
                new Book("Skipped Parts", "Robert Louis Stevenson", "memoir"),
                new Book("Atlas Shrugged: Part 1", "Albert Camus", "cookbook"),
                new Book("My Kid Could Paint That", "Thomas Mann", "fantasy"),
                new Book("If You Only Knew", "John Steinbeck", "cookbook"),
                new Book("Water Drops on Burning Rocks", "Toni Morrison", "health"),
                new Book("Harvest", "Sir Arthur Conan Doyle", "history"),
                new Book("Strapped", "Aldous Huxley", "development"),
                new Book("Flow: For Love of Water", "Charles Dickens", "romance"),
                new Book("Wrong Turn at Tahoe", "George R.R. Martin", "romance"),
                new Book("Girl, The", "J.D. Salinger", "art"),
                new Book("Frankenstein Meets the Wolf Man", "Homer", "art"),
                new Book("Jim Thorpe -- All-American", "Rabindranath Tagore", "art"),
                new Book("Fled", "Roald Dahl", "travel"),
                new Book("Bag of Hammers, A", "Maya Angelou", "humor"),
                new Book("Dirty Carnival, A (Biyeolhan geori)", "Jules Verne", "science fiction"),
                new Book("Aristocrats, The", "Jules Verne", "horror"),
                new Book("Pinky", "Plato", "humor"),
                new Book("Great Gabbo, The", "Stephen King", "classics"),
                new Book("To the Limit (Am Limit)", "William Faulkner", "romance"),
                new Book("Just Wright", "J.R.R. Tolkien", "cookbook"),
                new Book("Vampires, Les", "Jules Verne", "romance"),
                new Book("Wish You Were Here", "Johann Wolfgang von Goethe", "adventure"),
                new Book("Powder Room", "Jules Verne", "horror"),
                new Book("The Last Time I Saw Macao", "Aleksandr Sergeyevich Pushkin", "health"),
                new Book("Klansman, The", "Ralph Waldo Emerson", "humor"),
                new Book("Rebels of the Neon God (Qing shao nian nuo zha)", "Phillip Roth", "thriller"),
                new Book("Independencia", "W H Auden", "history"),
                new Book("Peter & the Wolf", "Aldous Huxley", "adventure"),
                new Book("Simple Life, A (Tao jie)", "Tennessee Williams", "memoir"),
                new Book("Dragon Inn (Sun lung moon hak chan)", "J.D. Salinger", "fantasy"),
                new Book("Reckless", "T.S.Eliot", "motivational"),
                new Book("Free to Play", "Hunter S. Thompson", "history"),
                new Book("Zoo", "Evelyn Waugh", "thriller"),
                new Book("Slender Thread, The", "Ralph Waldo Emerson", "crime"),
                new Book("Tracey Fragments, The", "Fyodor Dostoevsky", "memoir"),
                new Book("Free Range (Ballaad maailma heakskiitmisest)", "Henry Miller", "history"),
                new Book("Incredible Mr. Limpet, The", "Jules Verne", "adventure"),
                new Book("My Bodyguard", "Douglas Adams", "art"),
                new Book("V. I. Warshawski", "Toni Morrison", "history"),
                new Book("Riviera", "Hunter S. Thompson", "classics"),
                new Book("Fight, Zatoichi, Fight", "Jorge Luis Borges", "art"),
                new Book("Legend of the Boneknapper Dragon", "Hunter S. Thompson", "adventure"),
                new Book("On the Double ", "Rabindranath Tagore", "humor"),
                new Book("Bird of the Air, A (Loop, The)", "Ralph Waldo Emerson", "fantasy"),
                new Book("Godzilla vs. The Sea Monster", "Honore de Balzac", "thriller"),
                new Book("Heiter bis wolkig", "Roald Dahl", "romance"),
                new Book("Sicko", "Salman Rushdie", "thriller"),
                new Book("Love You You", "Honore de Balzac", "adventure"),
                new Book("Within the Woods", "Honore de Balzac", "development"),
                new Book("Farewell to Matyora", "Anton Chekhov", "motivational"),
                new Book("Clandestine Childhood", "Jack Kerouac", "self-help"),
                new Book("Paul Williams Still Alive", "Anne Frank", "development"),
                new Book("The Conrad Boys", "Czeslaw Milosz", "classics"),
                new Book("My Amityville Horror", "Charlotte Bronte", "health"),
                new Book("In the Name of the King III", "Plato", "memoir"),
                new Book("Project Grizzly", "Stephen King", "humor"),
                new Book("Misérables, Les", "Charles Dickens", "science fiction"),
                new Book("Harvest (Stadt Land Fluss)", "Marcel Proust", "adventure"),
                new Book("Thrill of Brazil, The", "Stephen King", "cookbook"),
                new Book("Transformers: Age of Extinction", "Jack Kerouac", "humor"),
                new Book("Bring on the Night", "Anne Frank", "history"),
                new Book("Abandon", "Johann Wolfgang von Goethe", "fantasy"),
                new Book("Private Resort", "John Keats", "travel"),
                new Book("Dog, The", "Toni Morrison", "romance"),
                new Book("Besieged (a.k.a. L'Assedio)", "William Faulkner", "mystery"),
                new Book("Art of Travel, The", "Homer", "crime"),
                new Book("Hysteria: The Def Leppard Story", "Albert Camus", "self-help"),
                new Book("Johnny Handsome", "John Milton", "classics"),
                new Book("Tourist, The", "Marcus Aurelius", "fantasy"),
                new Book("Hollidaysburg", "Paulo Coelho", "romance"),
                new Book("Big Nothing", "William Butler Yeats", "art"),
                new Book("That Certain Woman", "Rudyard Kipling", "art"),
                new Book("Hard Candy", "Joseph Heller", "motivational"),
                new Book("Skeletons", "Agatha Christie", "adventure"),
                new Book("Typhoon (Tae-poong)", "Honore de Balzac", "crime"),
                new Book("First Texan, The", "John Updike", "art"),
                new Book("Me You Them (Eu, Tu, Eles)", "Jack Kerouac", "thriller"),
                new Book("Crush", "Paulo Coelho", "cookbook"),
                new Book("Best Friends", "Robert Louis Stevenson", "art"),
                new Book("Red Dog", "Homer", "adventure"),
                new Book("Last Days, The", "Marcus Aurelius", "romance"),
                new Book("Red Dog", "Ernest Hemingway", "science fiction"),
                new Book("Superproduction (Superprodukcja)", "Thomas Hardy", "mystery"),
                new Book("How to Irritate People", "Jane Austen", "adventure"),
                new Book("Striking Distance", "Gabriela Mistral", "horror"),
                new Book("Foreign Letters", "Robert Louis Stevenson", "fantasy"),
                new Book("How She Move", "Phillip Roth", "health"),
                new Book("Misérables, Les", "Agatha Christie", "adventure"),
                new Book("Bottle Shock", "Charlotte Bronte", "classics"))
        );

        BookManagementService.getInstance().setBooks(books);
    }

    private static void collectUsers() {
        users = new ArrayList<>(Arrays.asList(new Librarian("Emmaline", "McNeilly", "4609 Lakewood Point", 864879778210l, "emcneilly0@wordpress.com", "UOIBTReU1L", 3000.0, 1),
                new Clerk("Lorrin", "Soloway", "9 Artisan Junction", 622931381714l, "lsoloway1@cam.ac.uk", "e9gIIOTBFcK", 2300.0, 3),
                new Clerk("Carla", "O'Hartnedy", "57944 Springview Point", 73956181600l, "cohartnedy2@tuttocitta.it", "sLxgrQAL91", 1900.0, 5),
                new Borrower("Hallsy", "Brims", "2497 Bunker Hill Court", 927017732041l, "hbrims3@jugem.jp", "F3WqEasVOH"),
                new Borrower("Bob", "Thaxter", "8918 Clove Plaza", 863977596029l, "bthaxter4@slashdot.org", "fbYRdXTGW"),
                new Borrower("Jammal", "Doulden", "47 Badeau Place", 625692785206l, "jdoulden5@furl.net", "rhBDE1Tly"),
                new Borrower("Cassandry", "Hawarden", "0572 High Crossing Crossing", 76972916973l, "chawarden6@irs.gov", "sfHIzr6b"),
                new Borrower("Lane", "Chad", "12719 Magdeline Place", 5028682732622l, "lchad7@oaic.gov.au", "gQtgxaIsv"),
                new Borrower("Brandise", "Littlejohn", "84247 Oakridge Court", 3517452580679l, "blittlejohn8@gizmodo.com", "RgicdejRI"),
                new Borrower("Godfree", "Licciardi", "7266 Norway Maple Street", 864317923500l, "glicciardi9@a8.net", "aPxZI4vPn")
        ));

        userService.setUsers(users);
    }

    private static void collectHoldReq() {
        if (users.isEmpty()) {
            users = userService.getUsers();
        }
        if (books.isEmpty()) {
            books = bookService.getAllBooksInLibrary();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(resourcesPath + "HoldRequests.txt"));
             Scanner scanner = new Scanner(reader)) {

            while (scanner.hasNextLine()) {
                scanner.useDelimiter("\\t");
                int borrowerId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                int bookId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                LocalDate reqDate = LocalDate.parse(scanner.nextLine());

                Borrower borrower = (Borrower) getUser(borrowerId);
                Book book = getBook(bookId);

                HoldRequest holdRequest = new HoldRequest(borrower, book);
                holdRequest.setRequestDate(reqDate);

                borrower.addHoldRequest(holdRequest);
                holdRequests.add(holdRequest);
            }

            holdReqService.setHoldRequests(holdRequests);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Person getUser(int id) {
        for (Person user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private static Book getBook(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return book;
            }
        }
        return null;
    }

}
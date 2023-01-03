package com.metodipaskov.services;

import com.metodipaskov.entities.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookManagementService {

    private static BookManagementService instance;
    private List<Book> books;

    private BookManagementService() {
//        this.books = new ArrayList<>();
        this.books = new ArrayList<>(Arrays.asList(
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
                new Book("Bottle Shock", "Charlotte Bronte", "classics")
        ));
    }

    public void setBooks(List<Book> bk) {
        books = bk;
    }

    public static BookManagementService getInstance() {
        if (instance == null) {
            instance = new BookManagementService();
        }
        return instance;
    }

    public List<Book> getAllBooksInLibrary() {
        return books;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> filteredBooksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                filteredBooksByAuthor.add(book);
            }
        }
        return filteredBooksByAuthor;
    }

    public List<Book> getBooksByGenre(String genre) {
        List<Book> filteredByGenreBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equals(genre)) {
                filteredByGenreBooks.add(book);
            }
        }
        return filteredByGenreBooks;
    }

    public void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("The book you are trying to add is already added to the library!\nCheck its details and update it if needed.");
            return;
        }
        books.add(book);
        System.out.println("Book successfully added to library.");
    }

    public void removeBook(Book book) {
        if (!books.contains(book)) {
            System.out.println("The book you are trying to remove is not present in the library!");
            return;
        }
        books.remove(book);
        System.out.println("Book removed successfully from library!");
    }

    public void updateBook(Book book, String title, String author, String genre) {
        if (!books.contains(book)) {
            System.out.println("The book you are trying to update is not in the library!");
            return;
        }

        if (title != null && !title.isBlank() && !title.isEmpty()) {
            book.setTitle(title);
            System.out.println("The books title is successfully updated!");
        }

        if (author != null && !author.isBlank() && !author.isEmpty()) {
            book.setAuthor(author);
            System.out.println("The books author is successfully updated!");
        }

        if (genre != null && !genre.isBlank() && !genre.isEmpty()) {
            book.setGenre(genre);
            System.out.println("The books genre is successfully updated!");
        }
    }
}

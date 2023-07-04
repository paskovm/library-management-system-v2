package com.metodipaskov.services;

import com.metodipaskov.entities.Book;
import com.metodipaskov.utils.PopulateLibrary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagementServiceTest {

    private static final BookManagementService bookService = BookManagementService.getInstance();

    @BeforeAll
    public static void loadResources() {
        PopulateLibrary.populate();
    }

    @Test
    public void getBookByIdSuccess() {
        int bookId = 1;
        Book book = bookService.getBookById(bookId);
        assertEquals("Children of the Corn V: Fields of Terror", book.getTitle());
    }

    @Test
    public void getBookByIdFailure() {
        int bookId = 1001;
        Book book = bookService.getBookById(bookId);
        assertNull(book);
    }

    @Test
    public void getBookByTitleSuccess() {
        String title = "On Dangerous Ground";
        Book book = bookService.getBookByTitle(title);
        assertEquals(book.getTitle(), title);
    }

    @Test
    public void getBookByTitleFailure() {
        String title = "My Test Title 123";
        Book book = bookService.getBookByTitle(title);
        assertNull(book);
    }

    @Test
    public void getBooksByAuthorSuccess() {
        String author = "Stephen King";
        List<Book> books = bookService.getBooksByAuthor(author);
        assertTrue(books.size() > 0);
    }

    @Test
    public void getBooksByAuthorFailure() {
        String author = "Author123";
        List<Book> books = bookService.getBooksByAuthor(author);
        assertFalse(books.size() > 0);
    }

    @Test
    public void addBookSuccess() {
        String title = "My Book";
        String author = "Metodi Paskov";
        String genre = "adventure";

        Book book = new Book(title, author, genre);
        bookService.addBook(book);

        assertNotNull(bookService.getBookByTitle(title));
    }

    @ParameterizedTest
    @ValueSource(strings = {"mystery", "horror", "adventure"})
    public void getBooksByGenreSuccess(String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        assertTrue(books.size() > 0);
    }
}
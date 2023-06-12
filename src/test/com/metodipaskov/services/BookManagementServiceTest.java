package com.metodipaskov.services;


import com.metodipaskov.entities.Book;
import com.metodipaskov.utils.PopulateLibrary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookManagementServiceTest {

    private static BookManagementService bookService = BookManagementService.getInstance();

    @BeforeAll
    public static void loadResources() {
        PopulateLibrary.populate();
    }

    @Test
    public void test() {
        Book book = bookService.getBookById(1);
        assertNotNull(book);

        assertEquals("Children of the Corn V: Fields of Terror", book.getTitle());
    }

}
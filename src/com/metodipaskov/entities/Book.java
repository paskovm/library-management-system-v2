package com.metodipaskov.entities;

import java.util.Comparator;

public class Book implements Comparable<Book> {

    private int bookId;
    private String title;
    private String author;
    private String genre;
    private boolean isIssued;
    private static int idIdx = 1;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isIssued = false;

        this.bookId = idIdx;
        idIdx++;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != this.getClass())
            return false;
        Book book = (Book) obj;
        return this.title.equals(book.getTitle()) && this.author.equals(book.getAuthor());
    }

    public void printInfo() {
        System.out.println("\nBook details are:");
        System.out.println("===================");
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Genre: " + this.genre);
        System.out.println("The book is available for loan: " + (!isIssued ? "yes" : "no"));
    }

    @Override
    public int compareTo(Book book) {
        return this.title.compareTo(book.getTitle());
    }

    @Override
    public String toString() {
        return this.title;
    }
}

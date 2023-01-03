package com.metodipaskov.utils;

import com.metodipaskov.entities.Book;
import com.metodipaskov.entities.HoldRequest;
import com.metodipaskov.entities.Loan;
import com.metodipaskov.entities.actors.Borrower;
import com.metodipaskov.entities.actors.Person;
import com.metodipaskov.entities.actors.Staff;
import com.metodipaskov.services.BookManagementService;
import com.metodipaskov.services.HoldRequestManagementService;
import com.metodipaskov.services.LoanManagementService;
import com.metodipaskov.services.UserManagementService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

//    public static void main(String[] args) {
//        collectLoans();
//        collectHoldReq();
//        displayLoans();
//        displayHoldReq();
//    }

    public static void collectHoldReq() {
        if (users.isEmpty()) {
            users = userService.getUsers();
        }
        if (books.isEmpty()) {
            books = bookService.getAllBooksInLibrary();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\resources\\HoldRequests.txt"));
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

    public static void collectLoans() {
        if (users.isEmpty()) {
            users = userService.getUsers();
        }
        if (books.isEmpty()) {
            books = bookService.getAllBooksInLibrary();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\resources\\Loan.txt"));
             Scanner scanner = new Scanner(reader)) {

            scanner.useDelimiter(",");
            String input = "";

            while (scanner.hasNextLine()) {

                scanner.skip("\\,?\\s?[A-Za-z].*\\(");
                int borrowerId = scanner.nextInt();
                scanner.skip(scanner.delimiter() + "\\s");
                int bookId = scanner.nextInt();
                scanner.skip(scanner.delimiter() + "\\s");
                int issuerId = scanner.nextInt();
                scanner.skip(scanner.delimiter() + "\\s");
                LocalDate dateIssued = LocalDate.parse(scanner.next().replaceAll("\'", ""));
                scanner.skip(scanner.delimiter() + "\\s");

                String temp = scanner.next();
                LocalDate dateReturned = null;
                Integer receiverId = null;
                boolean finePaid = false;
                boolean isIssued = true;

                if (!temp.startsWith("false")) {
                    receiverId = Integer.parseInt(temp);
                    scanner.skip(scanner.delimiter() + " ");
                    dateReturned = LocalDate.parse(scanner.next().replaceAll("\'", ""));
                    finePaid = true;
                    isIssued = false;
                }

                Person borrower = getUser(borrowerId);
                Person issuer = getUser(issuerId);
                Person receiver = receiverId != null ? getUser(receiverId) : null;
                Book book = getBook(bookId);

                Loan loan = new Loan((Borrower) borrower, book, (Staff) issuer);
                loan.setIssueDate(dateIssued);
                loan.setFinePaid(finePaid);

                if (receiver != null) {
                    loan.setReceiver((Staff) receiver);
                    loan.setReturnDate(dateReturned);
                }

                book.setIssued(isIssued);
                ((Borrower) borrower).addLoan(loan);
                loans.add(loan);

                input = scanner.nextLine();
            }

            collectLastLine(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectLastLine(String input) {

        Scanner scanner1 = new Scanner(input);
        scanner1.useDelimiter(",");
        scanner1.skip(scanner1.delimiter() + "\\s");
        scanner1.skip("[A-Za-z].*\\s\\(");
        int borrowerId = scanner1.nextInt();
        scanner1.skip(scanner1.delimiter() + "\\s");
        int bookId = Integer.parseInt(scanner1.next());
        scanner1.skip(scanner1.delimiter() + "\\s");
        int issuerId = scanner1.nextInt();
        scanner1.skip(scanner1.delimiter() + "\\s");
        LocalDate dateIssued = LocalDate.parse(scanner1.next().replaceAll("\'", ""));
        scanner1.skip(scanner1.delimiter() + "\\s");

        String temp = scanner1.next();
        LocalDate dateReturned = null;
        Integer receiverId = 0;
        boolean finePaid = false;
        boolean isIssued = true;

        if (!temp.startsWith("false")) {
            receiverId = Integer.parseInt(temp);

            scanner1.skip(scanner1.delimiter() + " ");
            dateReturned = LocalDate.parse(scanner1.next().replaceAll("\'", ""));
            finePaid = true;
            isIssued = false;
        }

        Person borrower = getUser(borrowerId);
        Person issuer = getUser(issuerId);
        Person receiver = receiverId != null ? getUser(receiverId) : null;
        Book book = getBook(bookId);

        Loan loan = new Loan((Borrower) borrower, book, (Staff) issuer);
        loan.setIssueDate(dateIssued);
        loan.setFinePaid(finePaid);

        if (receiver != null) {
            loan.setReceiver((Staff) receiver);
            loan.setReturnDate(dateReturned);
        }

        book.setIssued(isIssued);
        ((Borrower) borrower).addLoan(loan);
        loans.add(loan);

        loanService.setLoans(loans);
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

    private static void displayLoans() {
        for (Loan loan : loans)
            loan.printInfo();
    }

    private static void displayHoldReq() {
        for (HoldRequest holdRequest : holdRequests)
            holdRequest.printInfo();
    }
}
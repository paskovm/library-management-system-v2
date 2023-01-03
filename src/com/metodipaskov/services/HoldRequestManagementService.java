package com.metodipaskov.services;

import com.metodipaskov.entities.Book;
import com.metodipaskov.entities.HoldRequest;
import com.metodipaskov.entities.actors.Borrower;

import java.util.ArrayList;
import java.util.List;

public class HoldRequestManagementService {

    private static HoldRequestManagementService instance;
    private static List<HoldRequest> holdRequests;

    private HoldRequestManagementService() {
        holdRequests = new ArrayList<>();

    }

    public static HoldRequestManagementService getInstance() {
        if (instance == null) {
            instance = new HoldRequestManagementService();
        }
        return instance;
    }

    public void setHoldRequests(List<HoldRequest> hr) {
        holdRequests = hr;
    }

    public List<HoldRequest> getHoldRequests() {
        return holdRequests;
    }

    public List<HoldRequest> getHoldRequestsForUser(Borrower borrower) {
        List<HoldRequest> holdRequestsForUser = new ArrayList<>();
        for (HoldRequest holdRequest : holdRequests) {
            if (holdRequest.getBorrower().equals(borrower)) {
                holdRequestsForUser.add(holdRequest);
            }
        }
        return holdRequestsForUser;
    }

    public List<HoldRequest> getHoldRequestsForBook(Book book) {
        List<HoldRequest> holdRequestsForBook = new ArrayList<>();
        for (HoldRequest holdRequest : holdRequests) {
            if (holdRequest.getBook().equals(book)) {
                holdRequestsForBook.add(holdRequest);
            }
        }
        return holdRequestsForBook;
    }

    public void createHoldRequest(HoldRequest holdRequest) {
        holdRequests.add(holdRequest);
        holdRequest.getBorrower().addHoldRequest(holdRequest);
    }

    public void removeHoldRequest(HoldRequest holdRequest) {
        holdRequest.getBorrower().removeHoldRequest(holdRequest);
        holdRequests.remove(holdRequest);
    }
}

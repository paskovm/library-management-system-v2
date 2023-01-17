package com.metodipaskov;

import com.metodipaskov.menu.main.MainMenu;
import com.metodipaskov.utils.PopulateLibrary;

public class Main {

    /*
    * Admin:
    * admin, admin
    *
    * Librarian:
    * "emcneilly0@wordpress.com", "UOIBTReU1L"
    *
    * Clerk:
    * "lsoloway1@cam.ac.uk", "e9gIIOTBFcK"
    *
    * Borrower:
    * "glicciardi9@a8.net", "aPxZI4vPn"
    * */

    public static void main(String[] args) {
        PopulateLibrary.populate();

        new MainMenu().start();
    }
}
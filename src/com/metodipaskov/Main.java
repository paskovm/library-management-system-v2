package com.metodipaskov;

import com.metodipaskov.menu.main.MainMenu;
import com.metodipaskov.utils.PopulateLibrary;

public class Main {

    public static void main(String[] args) {
        PopulateLibrary.collectLoans();
        PopulateLibrary.collectHoldReq();

        new MainMenu().start();
    }
}
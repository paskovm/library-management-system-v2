package com.metodipaskov;

import com.metodipaskov.menu.main.MainMenu;
import com.metodipaskov.utils.PopulateLibrary;

public class Main {

    // "lsoloway1@cam.ac.uk", "e9gIIOTBFcK"

    public static void main(String[] args) {
        PopulateLibrary.populate();

        new MainMenu().start();
    }
}
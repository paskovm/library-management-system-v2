package com.metodipaskov.services;

import com.metodipaskov.utils.PopulateLibrary;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Hold Request Management System class tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HoldRequestManagementServiceTest {

    private static HoldRequestManagementService holdReqService;
    private static UserManagementService userService;



    @BeforeAll
    static void load() {
        PopulateLibrary.populate();
        holdReqService = HoldRequestManagementService.getInstance();
        userService = UserManagementService.getInstance();
    }

    @Test
    @DisplayName("This is a test method")
    void myTest() {
        assertTrue(true);
    }

    @Test
    void name_with_underscores_test() {
        assertTrue(true);
    }


}
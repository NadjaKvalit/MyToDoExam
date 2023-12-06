package functions;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;

import testbase.TestBase;
import pages.MainPage;

public class AddNewTask extends TestBase {
    @Test
    void addNewTask() {
        MainPage mainPage = new MainPage(page);
        mainPage.openPage();
        mainPage.getInputAddToDo().fill("Task 4");
        mainPage.getButtonAddNewTask().click();
        Locator lastNewTaskID = mainPage.getTasksList().locator("li:last-child");
        
    }
    }


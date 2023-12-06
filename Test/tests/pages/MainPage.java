package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pagebase.PageBase;

public class MainPage extends PageBase {

    public MainPage() {
    };

    // Variables
    // String expectedCalendarUrl = "http://127.0.0.1:5500/Frontend/index.html";

    // Locate elements
    Locator inputAddToDo;
    Locator buttonAddNewTask;
    Locator tasksList;

    Locator showResultsButton;
    Locator libraryCheckbox;
    Locator libraryToChooseLabel;

    public MainPage(Page page) {
        this.page = page;
        this.inputAddToDo = page.getByPlaceholder("Add a ToDo");
        this.buttonAddNewTask = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+"));
        this.tasksList = page.getByRole(AriaRole.LIST);

    }

    public Locator getInputAddToDo() {
        return inputAddToDo;
    }

    public Locator getButtonAddNewTask() {
        return buttonAddNewTask;
    }

    public Locator getTasksList() {
        return tasksList;
    }    

    public void addNewTask() {
        inputAddToDo.fill("Task 4");
        buttonAddNewTask.click();
    }

}

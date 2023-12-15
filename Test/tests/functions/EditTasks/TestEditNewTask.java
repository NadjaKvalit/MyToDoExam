package functions.EditTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBase;
import todo_api.GETTaskByID;
import pages.MainPage;

public class TestEditNewTask extends TestBase {
    @Test
    void editNewTaskChrome() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        String editNewToDo = "Task 5";
        
        // Locators
        Locator editButtonOfNewTask;
        Locator deleteButtonOfNewTask;
        Locator newTaskListItem;
        Locator editInputOfNewTask;
        Locator logo;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        logo = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("MyToDo"));
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        newTaskListItem = mainPage.getTaskListItem(idOfNewTaskListItem);
        editButtonOfNewTask = newTaskListItem.getByTestId("editButton");
        editButtonOfNewTask.click();

        editInputOfNewTask = newTaskListItem.getByTestId("editInput");
        assertThat(editInputOfNewTask).hasText(mainPage.getNewToDo()); //The input field of the task is appeared containing the current text
        assertThat(editInputOfNewTask).isEditable(); //The input field is available for editing.
        editInputOfNewTask.fill(editNewToDo);
       // page.waitForTimeout(1000);
        assertThat(editInputOfNewTask).hasText(editNewToDo); //The content of the input field is updated based on the editing description text
        //page.waitForTimeout(1000);
        logo.click();// Click outside the input field
        //newTaskListItem = mainPage.getTaskListItem(idOfNewTaskListItem);
        assertThat(newTaskListItem).hasText(editNewToDo); // Assert the new edited task description is displayed
        // Assert that Editing mode is inactive
        assertTrue(newTaskListItem.getByTestId("task_text").getAttribute("contenteditable") == null);

        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getWhatToDo(), editNewToDo); //Assert Task description for the task is updated in the database.
        assertThat(apiResponse).isOK(); // Response status is OK

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();
    }   
}


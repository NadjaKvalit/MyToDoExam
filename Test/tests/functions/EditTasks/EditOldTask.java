package functions.EditTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.GETTaskByID;
import pages.MainPage;

public class EditOldTask extends TestBase {
    @Test
    void editOldTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldTaskListItem;
        String editNewToDo = "Task 5";
        
        // Locators
        Locator editButtonOfOldTask;
        Locator oldTaskListItem;
        Locator editInputOfOldTask;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldTaskListItem = mainPage.getTask1IdOfListItem();
        oldTaskListItem = mainPage.getTaskListItem(idOfOldTaskListItem);
        editButtonOfOldTask = oldTaskListItem.getByTestId("editButton");
        editButtonOfOldTask.click();

        editInputOfOldTask = oldTaskListItem.getByTestId("editInput");
        editInputOfOldTask.fill(editNewToDo);
        
        page.click("body"); // Click outside the input field

        assertThat(oldTaskListItem).hasText(editNewToDo); // Assert the new edited task description is displayed
        // Assert that Editing mode is inactive
        assertTrue(oldTaskListItem.getByTestId("task_text").getAttribute("contenteditable") == null);
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getWhatToDo(), editNewToDo); //Assert Task description for the task is updated in the database.
        assertThat(apiResponse).isOK(); // Response status is OK
    }   
}



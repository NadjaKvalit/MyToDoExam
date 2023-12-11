package functions.CompleteTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.GetTaskByID;
import pages.MainPage;

public class CompleteOldTask extends TestBase {
    @Test
    void completeOldTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldUncompletedTaskListItem;
        
        // Locators
        Locator doneOfOldTask;
        //Locator deleteButtonOfNewTask;
        Locator taskListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldUncompletedTaskListItem = mainPage.getTask2IdOfListItem();
        taskListItem = mainPage.getTaskListItem(idOfOldUncompletedTaskListItem);
        doneOfOldTask = taskListItem.getByTestId("done_status");
        doneOfOldTask.check();
        assertThat(doneOfOldTask).isChecked(); // Assert done status is completed, The checkbox is checked.
        //Done-status for the task is updated into doneID = 1.
        assertThat(doneOfOldTask).hasId(String.valueOf((mainPage.getDoneStatusIdOfCompletedTask())));
        
        assertThat(taskListItem.locator(".toDoTextBlock")).hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldUncompletedTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GetTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GetTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfCompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK
    }
}


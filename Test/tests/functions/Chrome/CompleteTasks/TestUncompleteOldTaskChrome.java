package functions.Chrome.CompleteTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBaseChrome;
import todo_api.GETTaskByID;
import pages.MainPage;

public class TestUncompleteOldTaskChrome extends TestBaseChrome {
    @Test
    void uncompleteOldTaskChrome() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldCompletedTaskListItem;
        
        // Locators
        Locator doneOfOldTask;
        Locator taskListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldCompletedTaskListItem = mainPage.getTask1IdOfListItem();
        taskListItem = mainPage.getTaskListItem(idOfOldCompletedTaskListItem);
        doneOfOldTask = taskListItem.getByTestId("done_status");
        doneOfOldTask.uncheck();
        assertThat(doneOfOldTask).not().isChecked(); // Assert done status is uncompleted, The checkbox is not checked.
        //Done-status for the task is updated into doneID = 2.
        assertThat(doneOfOldTask).hasId(String.valueOf((mainPage.getDoneStatusIdOfUncompletedTask())));
        
        assertThat(taskListItem.locator(".toDoTextBlock")).not().hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldCompletedTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfUncompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK
    }
}

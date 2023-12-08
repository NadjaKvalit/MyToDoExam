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

public class UncompleteNewTask extends TestBase {
    @Test
    void uncompleteNewTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        
        // Locators
        Locator doneOfNewTask;
        Locator deleteButtonOfNewTask;
        Locator newTaskListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        newTaskListItem = mainPage.getNewTaskListItem();
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        doneOfNewTask = newTaskListItem.getByTestId("done_status");
        doneOfNewTask.check();
        doneOfNewTask.uncheck();
        assertThat(doneOfNewTask).not().isChecked(); // Assert done status is uncompleted, The checkbox is not checked.
        //Done-status for the task is updated into doneID = 2.
        assertThat(doneOfNewTask).hasId(String.valueOf((mainPage.getDoneStatusIdOfUncompletedTask())));
        
        assertThat(newTaskListItem.locator(".toDoTextBlock")).not().hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GetTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GetTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfUncompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();
    }
}

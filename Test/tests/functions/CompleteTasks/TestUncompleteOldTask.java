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
import todo_api.GETTaskByID;
import pages.MainPage;

public class TestUncompleteOldTask extends TestBase {
    @Test
    void uncompleteOldTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldCompletedTaskListItem1;
        String idOfOldCompletedTaskListItem4;
        
        // Locators
        Locator doneOfOldTask1;
        Locator doneOfOldTask4;
        Locator taskListItem1;
        Locator taskListItem4;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldCompletedTaskListItem1 = mainPage.getTask1IdOfListItem();
        idOfOldCompletedTaskListItem4 = mainPage.getTask4IdOfListItem();
        taskListItem1 = mainPage.getTaskListItem(idOfOldCompletedTaskListItem1);
        taskListItem4 = mainPage.getTaskListItem(idOfOldCompletedTaskListItem4);
        doneOfOldTask1 = taskListItem1.getByTestId("done_status");
        doneOfOldTask4 = taskListItem4.getByTestId("done_status");
        if(doneOfOldTask1.isChecked()){
        doneOfOldTask1.uncheck();
        assertThat(doneOfOldTask1).not().isChecked(); // Assert done status is uncompleted, The checkbox is not checked.
        //Done-status for the task is updated into doneID = 2.
        assertThat(doneOfOldTask1).hasId(String.valueOf((mainPage.getDoneStatusIdOfUncompletedTask())));
        
        assertThat(taskListItem1.locator(".toDoTextBlock")).not().hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldCompletedTaskListItem1);

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
    else{
        doneOfOldTask4.uncheck();
        assertThat(doneOfOldTask4).not().isChecked(); // Assert done status is uncompleted, The checkbox is not checked.
        //Done-status for the task is updated into doneID = 2.
        assertThat(doneOfOldTask4).hasId(String.valueOf((mainPage.getDoneStatusIdOfUncompletedTask())));
        
        assertThat(taskListItem4.locator(".toDoTextBlock")).not().hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldCompletedTaskListItem4);

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
}}

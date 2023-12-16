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

public class TestCompleteOldTask extends TestBase {
    @Test
    void completeOldTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldUncompletedTaskListItem2;
        String idOfOldUncompletedTaskListItem3;
        
        // Locators
        Locator doneOfOldTask2;
        Locator doneOfOldTask3;
        //Locator deleteButtonOfNewTask;
        Locator taskListItem2;
        Locator taskListItem3;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldUncompletedTaskListItem2 = mainPage.getTask2IdOfListItem();
        idOfOldUncompletedTaskListItem3 = mainPage.getTask3IdOfListItem();
        taskListItem2 = mainPage.getTaskListItem(idOfOldUncompletedTaskListItem2);
        taskListItem3 = mainPage.getTaskListItem(idOfOldUncompletedTaskListItem3);
        doneOfOldTask2 = taskListItem2.getByTestId("done_status");
        doneOfOldTask3 = taskListItem3.getByTestId("done_status");
        if(!doneOfOldTask2.isChecked()){
        doneOfOldTask2.check();
        assertThat(doneOfOldTask2).isChecked(); // Assert done status is completed, The checkbox is checked.
        //Done-status for the task is updated into doneID = 1.
        assertThat(doneOfOldTask2).hasId(String.valueOf((mainPage.getDoneStatusIdOfCompletedTask())));
        
        assertThat(taskListItem2.locator(".toDoTextBlock")).hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldUncompletedTaskListItem2);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfCompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK
    }
    else{
        doneOfOldTask3.check();
        assertThat(doneOfOldTask3).isChecked(); // Assert done status is completed, The checkbox is checked.
        //Done-status for the task is updated into doneID = 1.
        assertThat(doneOfOldTask3).hasId(String.valueOf((mainPage.getDoneStatusIdOfCompletedTask())));
        
        assertThat(taskListItem3.locator(".toDoTextBlock")).hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldUncompletedTaskListItem2);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfCompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK
    }
    }
}


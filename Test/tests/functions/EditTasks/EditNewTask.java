package functions.EditTasks;

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

public class EditNewTask extends TestBase {
    @Test
    void editNewTask() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        String newToDo = "Task 5";
        
        // Locators
        Locator editButtonOfNewTask;
        Locator deleteButtonOfNewTask;
        Locator newTaskListItem;
        Locator editInputOfNewTask;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        newTaskListItem = mainPage.getNewTaskListItem();
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        editButtonOfNewTask = newTaskListItem.getByTestId("editButton");
        editButtonOfNewTask.click();

        editInputOfNewTask = newTaskListItem.getByTestId("editInput");
        assertThat(editInputOfNewTask).hasText(mainPage.getNewToDo()); //The input field of the task is appeared containing the current text
        assertThat(editInputOfNewTask).isEditable(); //The input field is available for editing.
        editInputOfNewTask.fill(newToDo);

        /*
        
        assertThat(doneOfNewTask).isChecked(); // Assert done status is completed, The checkbox is checked.
        //Done-status for the task is updated into doneID = 1.
        assertThat(doneOfNewTask).hasId(String.valueOf((mainPage.getDoneStatusIdOfCompletedTask())));
        
        assertThat(newTaskListItem.locator(".toDoTextBlock")).hasCSS("text-decoration", Pattern.compile(".*line-through.*"));
        
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GetTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GetTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getDone_idDone(), mainPage.getDoneStatusIdOfCompletedTask()); //Done-status for the task is updated in the database into doneID = 1.
        assertThat(apiResponse).isOK(); // Response status is OK

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();*/
    }   
}


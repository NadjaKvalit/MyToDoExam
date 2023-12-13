package functions.TasksPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.GETTaskByID;
import pages.MainPage;

public class TestUpdateOldTaskPriority extends TestBase {
    
    @Test
    void updateOldTaskPriorityy() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldTaskListItem;
        String newPriority = "medium";
        int newPriorityIdOfOldTask = mainPage.getPriorityId(newPriority);
        
        // Locators
        Locator priorityButtonOfOldTask;
        Locator oldTaskListItem;
        Locator selectPriorityIcon;
        Locator priorityModal;
        Locator selectedPriorityIcon;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldTaskListItem = mainPage.getTask1IdOfListItem();
        oldTaskListItem = mainPage.getTaskListItem(idOfOldTaskListItem);

        priorityButtonOfOldTask = oldTaskListItem.getByTestId("priority");
        priorityButtonOfOldTask.click();
        priorityModal = page.getByTestId("priority_modal");

        selectPriorityIcon = priorityModal.getByAltText(newPriority);
        selectPriorityIcon.click();

        selectedPriorityIcon = priorityButtonOfOldTask.locator("img");
        assertThat(selectedPriorityIcon).hasAttribute("alt", newPriority);
        assertThat(priorityButtonOfOldTask).hasId(Integer.toString(mainPage.getPriorityId(newPriority)));

        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getIdTasks(), idOfOldTaskListItem);
        assertEquals(getTaskByIDResponse.getPriority_idPriority(), newPriorityIdOfOldTask);

        assertThat(apiResponse).isOK(); // Response status is OK
    }
}


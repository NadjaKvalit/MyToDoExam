package functions.Firefox.TasksPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBaseFirefox;
import todo_api.GETTaskByID;
import pages.MainPage;

public class TestSetNewTaskPriorityFirefox extends TestBaseFirefox {
    
    @Test
    void setNewTaskPriorityFirefox() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        String newPriority = "low";
        int newPriorityIdOfNewTask = mainPage.getPriorityId(newPriority);
        Set<String> expectedAltAttributesInModal = Set.of("high", "medium", "low");
        Set<String> actualAltAttributesInModal = new HashSet<>();

        // Locators
        Locator deleteButtonOfNewTask;
        Locator priorityButtonOfNewTask;
        Locator newTaskListItem;
        Locator selectPriorityIcon;
        Locator priorityModal;
        List<Locator> imagesInPriorityModal;
        Locator selectedPriorityIcon;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        newTaskListItem = mainPage.getTaskListItem(idOfNewTaskListItem);

        priorityButtonOfNewTask = newTaskListItem.getByTestId("priority");
        priorityButtonOfNewTask.click();
        priorityModal = page.getByTestId("priority_modal");

        assertThat(priorityModal).isVisible(); // A modal displaying a list of category icons is popped up.
        imagesInPriorityModal = priorityModal.locator("img").all();
        for (Locator image : imagesInPriorityModal) {
            String alt = image.getAttribute("alt");
            actualAltAttributesInModal.add(alt);
        }

        // Assert that The list of category in the modal contains 5 names:
        assertEquals(3, imagesInPriorityModal.size());
        assertEquals(expectedAltAttributesInModal, actualAltAttributesInModal);

        selectPriorityIcon = priorityModal.getByAltText(newPriority);
        selectPriorityIcon.click();

        selectedPriorityIcon = priorityButtonOfNewTask.locator("img");
        assertThat(selectedPriorityIcon).hasAttribute("alt", newPriority);
        assertThat(priorityButtonOfNewTask).hasId(Integer.toString(mainPage.getPriorityId(newPriority)));

        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getIdTasks(), idOfNewTaskListItem);
        assertEquals(getTaskByIDResponse.getPriority_idPriority(), newPriorityIdOfNewTask);

        assertThat(apiResponse).isOK(); // Response status is OK

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();
    }
}

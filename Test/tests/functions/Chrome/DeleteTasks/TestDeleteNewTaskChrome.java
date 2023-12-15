package functions.Chrome.DeleteTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBaseChrome;
import pages.MainPage;

public class TestDeleteNewTaskChrome extends TestBaseChrome {
    @Test
    void deleteNewTaskChrome() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        
        // Locators
        Locator deleteButtonOfNewTask;
        Locator newTaskListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        newTaskListItem = mainPage.getTaskListItem(idOfNewTaskListItem);
        assertThat(newTaskListItem).isVisible();

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();
        page.waitForTimeout(1000);
        assertThat(newTaskListItem).hasCount(0);
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);
        assertEquals(apiResponse.status(), 404);
    }       
}


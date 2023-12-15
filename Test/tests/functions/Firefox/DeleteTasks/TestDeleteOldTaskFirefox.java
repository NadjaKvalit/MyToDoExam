package functions.Firefox.DeleteTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBaseFirefox;
import pages.MainPage;

public class TestDeleteOldTaskFirefox extends TestBaseFirefox {
    @Test
    void deleteOldTaskFirefox() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldTaskListItem;
        
        // Locators
        Locator deleteButtonOfOldTask;
        Locator oldTaskListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldTaskListItem = mainPage.getTask3IdOfListItem();
        oldTaskListItem = mainPage.getTaskListItem(idOfOldTaskListItem);
        assertThat(oldTaskListItem).isVisible();

        deleteButtonOfOldTask = oldTaskListItem.getByTestId("deleteButton");
        deleteButtonOfOldTask.click();
        page.waitForTimeout(1000);
        assertThat(oldTaskListItem).hasCount(0);
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem);
        assertEquals(apiResponse.status(), 404);
    }   
}

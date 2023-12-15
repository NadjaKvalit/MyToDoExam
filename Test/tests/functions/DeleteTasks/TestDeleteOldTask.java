package functions.DeleteTasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import pages.MainPage;

public class TestDeleteOldTask extends TestBase {
    @Test
    void deleteOldTaskChrome() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldTaskListItem5;
        String idOfOldTaskListItem6;
        
        // Locators
        Locator deleteButtonOfOldTask5;
        Locator deleteButtonOfOldTask6;
        Locator oldTaskListItem5;
        Locator oldTaskListItem6;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldTaskListItem5 = mainPage.getTask5IdOfListItem();
        idOfOldTaskListItem6 = mainPage.getTask6IdOfListItem();
        oldTaskListItem5 = mainPage.getTaskListItem(idOfOldTaskListItem5);
        oldTaskListItem6 = mainPage.getTaskListItem(idOfOldTaskListItem6);
        if(oldTaskListItem5.isVisible()){
        deleteButtonOfOldTask5 = oldTaskListItem5.getByTestId("deleteButton");
        deleteButtonOfOldTask5.click();
        page.waitForTimeout(1000);
        assertThat(oldTaskListItem5).hasCount(0);
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem5);
        assertEquals(apiResponse.status(), 404);
    }  
    else{
        deleteButtonOfOldTask6 = oldTaskListItem6.getByTestId("deleteButton");
        deleteButtonOfOldTask6.click();
        page.waitForTimeout(1000);
        assertThat(oldTaskListItem6).hasCount(0);
        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem6);
        assertEquals(apiResponse.status(), 404);
    } 
}
}

package functions.TasksCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.GetTaskByID;
import pages.MainPage;

public class UpdateOldTaskCategory extends TestBase {
    @Test
    void setNewTaskCategory() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfOldTaskListItem;
        String newCategory = "sport";
        int newCategoryIdOfOldTask = mainPage.getCategoryId(newCategory);

        // Locators
        Locator categoryButtonOfOldTask;
        Locator oldTaskListItem;
        Locator selectCategoryIcon;
        Locator categoryModal;
        Locator selectedCategoryIcon;

        // Interactions with elements and Assertions
        mainPage.openPage();
        idOfOldTaskListItem = mainPage.getTask2IdOfListItem();
        oldTaskListItem = mainPage.getTaskListItem(idOfOldTaskListItem);

        categoryButtonOfOldTask = oldTaskListItem.getByTestId("category");
        categoryButtonOfOldTask.click();
        categoryModal = page.getByTestId("category_modal");

        selectCategoryIcon = categoryModal.getByAltText(newCategory);
        selectCategoryIcon.click();

        selectedCategoryIcon = categoryButtonOfOldTask.locator("img");
        assertThat(selectedCategoryIcon).hasAttribute("alt", newCategory);
        assertThat(categoryButtonOfOldTask).hasId(Integer.toString(mainPage.getCategoryId(newCategory)));

        // API test, GET - method. Get the new created task fron db
        APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfOldTaskListItem);

        // Create Json serializer object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Deserialize JSON string to Java object
        GetTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GetTaskByID.class);

        // Verify that test data from DB in response is correct
        assertEquals(getTaskByIDResponse.getIdTasks(), idOfOldTaskListItem);
        assertEquals(getTaskByIDResponse.getCategory_idCategory(), newCategoryIdOfOldTask);

        assertThat(apiResponse).isOK(); // Response status is OK

        categoryButtonOfOldTask.click();
        selectCategoryIcon = page.getByAltText(mainPage.getCategoryName(mainPage.getTask2CategoryBlockId()));
        selectCategoryIcon.click();
    }
}

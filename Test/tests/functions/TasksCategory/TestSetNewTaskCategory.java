package functions.TasksCategory;

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
import testbase.TestBase;
import todo_api.GETTaskByID;
import pages.MainPage;

public class SetNewTaskCategory extends TestBase {
    @Test
    void setNewTaskCategory() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // Variables
        String idOfNewTaskListItem;
        String newCategory = "work";
        int newCategoryIdOfNewTask = mainPage.getCategoryId(newCategory);
        Set<String> expectedAltAttributesInModal = Set.of("work", "study", "grocery", "sport", "other");
        Set<String> actualAltAttributesInModal = new HashSet<>();

        // Locators
        Locator deleteButtonOfNewTask;
        Locator categoryButtonOfNewTask;
        Locator newTaskListItem;
        Locator selectCategoryIcon;
        Locator categoryModal;
        List<Locator> imagesInCategoryModal;
        Locator selectedCategoryIcon;

        // Interactions with elements and Assertions
        mainPage.openPage();
        mainPage.addNewTask();
        idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();
        newTaskListItem = mainPage.getTaskListItem(idOfNewTaskListItem);

        categoryButtonOfNewTask = newTaskListItem.getByTestId("category");
        categoryButtonOfNewTask.click();
        categoryModal = page.getByTestId("category_modal");

        assertThat(categoryModal).isVisible(); // A modal displaying a list of category icons is popped up.
        imagesInCategoryModal = categoryModal.locator("img").all();
        for (Locator image : imagesInCategoryModal) {
            String alt = image.getAttribute("alt");
            actualAltAttributesInModal.add(alt);
        }

        // Assert that The list of category in the modal contains 5 names:
        assertEquals(5, imagesInCategoryModal.size());
        assertEquals(expectedAltAttributesInModal, actualAltAttributesInModal);

        selectCategoryIcon = categoryModal.getByAltText(newCategory);
        selectCategoryIcon.click();

        selectedCategoryIcon = categoryButtonOfNewTask.locator("img");
        assertThat(selectedCategoryIcon).hasAttribute("alt", newCategory);
        assertThat(categoryButtonOfNewTask).hasId(Integer.toString(mainPage.getCategoryId(newCategory)));

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
        assertEquals(getTaskByIDResponse.getCategory_idCategory(), newCategoryIdOfNewTask);

        assertThat(apiResponse).isOK(); // Response status is OK

        deleteButtonOfNewTask = newTaskListItem.getByTestId("deleteButton");
        deleteButtonOfNewTask.click();
    }
}

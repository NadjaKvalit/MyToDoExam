package functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.GetTaskByID;
import pages.MainPage;

public class DisplayTasks extends TestBase {

    public static void updateOrInsertTask(String taskId, String taskName, int doneId, int categoryId, int priorityId) {
        String updatedTaskData = "{\n" +
                "  \"whatToDo\": \"" + taskName + "\",\n" +
                "  \"Done_idDone\": " + doneId + ",\n" +
                "  \"Category_idCategory\": " + categoryId + ",\n" +
                "  \"Priority_idPriority\": " + priorityId + "\n" +
                "}";
    
                Response response = RestAssured.get(taskId);

        //APIResponse apiResponse = RestAssured.get("http://localhost:3000/tasks/" + taskId);

        int statusCode = response.status();
    
        if (statusCode == 404) {
            // Task doesn't exist, so create it
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(updatedTaskData)
                    .when()
                    .post("/tasks")
                    .then()
                    .statusCode(200); // Assuming a successful creation returns status code 200
        } else if (statusCode == 200) {
            // Task exists, update it
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(updatedTaskData)
                    .when()
                    .put(taskId)
                    .then()
                    .statusCode(200); // Assuming a successful update returns status code 200
        }
    }

    @Test
    void displayTasks() {
        // Setup
        MainPage mainPage = new MainPage(page);

 String baseURI = "http://localhost:3000/tasks/"; // Update with your API base URI

        RestAssured.baseURI = baseURI;
        updateOrInsertTask("1701346042001", "Task 1", 1, 1, 4); // Update task with ID 1701346042001
        updateOrInsertTask("1701346042002", "Task 2", 2, 2, 1); // Update task with ID 1701346042002
        updateOrInsertTask("1701346042003", "Task 3", 1, 6, 2); // Update task with ID 1701346042003
    
        // Variables

        // Locators
        Locator task1ListItem;
        Locator task2ListItem;
        Locator task3ListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();

        // Assert that tasks cointains the list of correct number of tasks
        assertEquals(mainPage.getCountTestDataTasks(), mainPage.getTasksList().locator("li").count());
        task1ListItem = page.locator("//li[@id='" + mainPage.getTask1IdOfListItem() + "']");
        task2ListItem = page.locator("//li[@id='" + mainPage.getTask2IdOfListItem() + "']");
        task3ListItem = page.locator("//li[@id='" + mainPage.getTask3IdOfListItem() + "']");

        // Assert that the list of tasks is displayed
        assertThat(task1ListItem).isVisible();
        assertThat(task2ListItem).isVisible();
        assertThat(task3ListItem).isVisible();

        // Assert correctness of ToDo descriptions
        assertThat(task1ListItem).hasText(mainPage.getTask1ToDo());
        assertThat(task2ListItem).hasText(mainPage.getTask2ToDo());
        assertThat(task3ListItem).hasText(mainPage.getTask3ToDo());     
        
        // Assert corerctness of done statuses
        assertThat(task1ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask1DoneId()));
        assertThat(task2ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask2DoneId()));
        assertThat(task3ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask3DoneId()));

        // Assert corerctness of priorities
        assertThat(task1ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask1PriorityBlockId()));
        assertThat(task2ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask2PriorityBlockId()));
        assertThat(task3ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask3PriorityBlockId()));  


        // Assert corerctness of categories
        assertThat(task1ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask1CategoryBlockId()));
        assertThat(task2ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask2CategoryBlockId()));
        assertThat(task3ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask3CategoryBlockId()));

    }
}

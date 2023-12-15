package functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import testbase.TestBase;
import todo_api.POSTTask;
import pages.MainPage;

public class TestDisplayTasks extends TestBase {

    @Test
    void displayTasksChrome() {
        // Setup
        MainPage mainPage = new MainPage(page);

        // String baseURI = "http://localhost:3000/tasks/"; // Update with your API base
        // URI

        // RestAssured.baseURI = baseURI;
        POSTTask postTask = new POSTTask();
        postTask.updateOrInsertTask("1701346042001", "Task 1", 1, 1, 4, context); // Update task with ID 1701346042001
        postTask.updateOrInsertTask("1701346042002", "Task 2", 2, 2, 1, context); // Update task with ID 1701346042002
        postTask.updateOrInsertTask("1701346042003", "Task 3", 2, 6, 2, context); // Update task with ID 1701346042003
        postTask.updateOrInsertTask("1701346042004", "Task 4", 1, 3, 1, context); // Update task with ID 1701346042004
        postTask.updateOrInsertTask("1701346042005", "Task 5", 2, 4, 3, context); // Update task with ID 1701346042005
        postTask.updateOrInsertTask("1701346042006", "Task 6", 1, 5, 3, context); // Update task with ID 1701346042006

        // Variables

        // Locators
        Locator task1ListItem;
        Locator task2ListItem;
        Locator task3ListItem;
        Locator task4ListItem;
        Locator task5ListItem;
        Locator task6ListItem;

        // Interactions with elements and Assertions
        mainPage.openPage();

        // Assert that tasks cointains the list of correct number of tasks
        assertEquals(mainPage.getCountTestDataTasks(), mainPage.getTasksList().locator("li").count());
        task1ListItem = page.locator("//li[@id='" + mainPage.getTask1IdOfListItem() + "']");
        task2ListItem = page.locator("//li[@id='" + mainPage.getTask2IdOfListItem() + "']");
        task3ListItem = page.locator("//li[@id='" + mainPage.getTask3IdOfListItem() + "']");
        task4ListItem = page.locator("//li[@id='" + mainPage.getTask4IdOfListItem() + "']");
        task5ListItem = page.locator("//li[@id='" + mainPage.getTask5IdOfListItem() + "']");
        task6ListItem = page.locator("//li[@id='" + mainPage.getTask6IdOfListItem() + "']");

        // Assert that the list of tasks is displayed
        assertThat(task1ListItem).isVisible();
        assertThat(task2ListItem).isVisible();
        assertThat(task3ListItem).isVisible();
        assertThat(task4ListItem).isVisible();
        assertThat(task5ListItem).isVisible();
        assertThat(task6ListItem).isVisible();
        
        // Assert correctness of ToDo descriptions
        assertThat(task1ListItem).hasText(mainPage.getTask1ToDo());
        assertThat(task2ListItem).hasText(mainPage.getTask2ToDo());
        assertThat(task3ListItem).hasText(mainPage.getTask3ToDo());
        assertThat(task4ListItem).hasText(mainPage.getTask4ToDo());
        assertThat(task5ListItem).hasText(mainPage.getTask5ToDo());
        assertThat(task6ListItem).hasText(mainPage.getTask6ToDo());

        // Assert corerctness of done statuses
        assertThat(task1ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask1DoneId()));
        assertThat(task2ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask2DoneId()));
        assertThat(task3ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask3DoneId()));
        assertThat(task4ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask4DoneId()));
        assertThat(task5ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask5DoneId()));
        assertThat(task6ListItem.getByTestId("done_status")).hasId(String.valueOf(mainPage.getTask6DoneId()));

        // Assert corerctness of priorities
        assertThat(task1ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask1PriorityBlockId()));
        assertThat(task2ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask2PriorityBlockId()));
        assertThat(task3ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask3PriorityBlockId()));
        assertThat(task4ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask4PriorityBlockId()));
        assertThat(task5ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask5PriorityBlockId()));
        assertThat(task6ListItem.getByTestId("priority")).hasId(String.valueOf(mainPage.getTask6PriorityBlockId()));

        // Assert corerctness of categories
        assertThat(task1ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask1CategoryBlockId()));
        assertThat(task2ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask2CategoryBlockId()));
        assertThat(task3ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask3CategoryBlockId()));
        assertThat(task4ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask4CategoryBlockId()));
        assertThat(task5ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask5CategoryBlockId()));
        assertThat(task6ListItem.getByTestId("category")).hasId(String.valueOf(mainPage.getTask6CategoryBlockId()));

    }
}

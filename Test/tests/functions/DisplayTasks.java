package functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Locator;
import testbase.TestBase;
import pages.MainPage;

public class DisplayTasks extends TestBase {

    @Test
    void displayTasks() {
        // Setup
        MainPage mainPage = new MainPage(page);

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

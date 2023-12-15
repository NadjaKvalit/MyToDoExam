package functions;

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

public class TestAddNewTask extends TestBase {

  @Test
  void addNewTaskChrome() {
    // Setup
    MainPage mainPage = new MainPage(page);

    // Variables
    String newToDo = "Task 4";
    String backgroundColorOfFocusedInput = "rgb(235, 255, 232)";
    int doneIdOfNewTask = mainPage.getDoneIdOfNewTask();
    int priorityBlockIdOfNewTask = mainPage.getPriorityBlockIdOfNewTask();
    int categoryBlockIdOfNewTask = mainPage.getCategoryBlockIdOfNewTask();
    String idOfNewTaskListItem;

    // Locators
    Locator inputAddToDo;
    Locator lastListItem;
    Locator doneOfNewTask;
    Locator priorityBlockOfNewTask;
    Locator categoryBlockOfNewTask;
    Locator deleteButtonOfNewTask;

    // Interactions with elements and Assertions
    mainPage.openPage();
    inputAddToDo = mainPage.getInputAddToDo();
    inputAddToDo.click();
    assertThat(inputAddToDo).isFocused(); // The input field is focused
    assertThat(inputAddToDo).hasCSS("background-color", backgroundColorOfFocusedInput); // The input field background is changed to green

    inputAddToDo.fill(newToDo);
    assertEquals((inputAddToDo).inputValue(), newToDo); // The content of the input field is updated based on the description text

    mainPage.getButtonAddNewTask().click();
    lastListItem = mainPage.getTasksList().locator("li:last-child");
    assertThat(lastListItem).hasText(newToDo); //The new created task is appeared at the end of the task list
    idOfNewTaskListItem = mainPage.getiIdOfNewTaskListItem();

    doneOfNewTask = lastListItem.getByTestId("done_status");
    assertThat(doneOfNewTask).not().isChecked(); //done status is uncompleted
    assertThat(doneOfNewTask).hasId(String.valueOf(doneIdOfNewTask));

    priorityBlockOfNewTask = lastListItem.getByTestId("priority");
    assertThat(priorityBlockOfNewTask).hasId(String.valueOf(priorityBlockIdOfNewTask)); //no specified priority for the new created task

    categoryBlockOfNewTask = lastListItem.getByTestId("category");
    assertThat(categoryBlockOfNewTask).hasId(String.valueOf(categoryBlockIdOfNewTask)); //no specified category for the new created task
    
    assertThat(inputAddToDo).isEmpty(); //The input field is become empty after clicking "+" button.

    //API test, GET - method. Get the new created task fron db
    APIResponse apiResponse = page.request().get("http://localhost:3000/tasks/" + idOfNewTaskListItem);

    // Create Json serializer object
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    Gson gson = builder.create();

    // Deserialize JSON string to Java object
    GETTaskByID getTaskByIDResponse = gson.fromJson(apiResponse.text(), GETTaskByID.class);

    // Verify that test data from DB in response is correct
    assertEquals(getTaskByIDResponse.getIdTasks(), idOfNewTaskListItem);
    assertEquals(getTaskByIDResponse.getWhatToDo(), newToDo);
    assertEquals(getTaskByIDResponse.getDone_idDone(), doneIdOfNewTask);
    assertEquals(getTaskByIDResponse.getCategory_idCategory(), categoryBlockIdOfNewTask);
    assertEquals(getTaskByIDResponse.getPriority_idPriority(), priorityBlockIdOfNewTask);

    assertThat(apiResponse).isOK(); //Response status is OK

    deleteButtonOfNewTask = lastListItem.getByTestId("deleteButton");
    deleteButtonOfNewTask.click(); 
  }
}

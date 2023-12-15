package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pagebase.PageBase;

public class MainPage extends PageBase {

    public MainPage() {
    };

    // Variables
    String newToDo = "Task 7";
    int doneIdOfNewTask = 2;
    int priorityBlockIdOfNewTask = 4;
    int categoryBlockIdOfNewTask = 6;
    String idOfNewTaskListItem;
    int doneStatusIdOfCompletedTask = 1;
    int doneStatusIdOfUncompletedTask = 2;
    int priorityId;
    String priorityName;
    int categoryId;
    String categoryName;

    // TESTDATA
    int countTestDataTasks = 6;
    // Task 1
    String Task1IdOfListItem = "1701346042001";
    String Task1ToDo = "Task 1";
    int Task1DoneId = 1; // done status "completed"
    int Task1CategoryBlockId = 1; // category "work"
    int Task1PriorityBlockId = 4; // priority "no priority"
    // Task 2
    String Task2IdOfListItem = "1701346042002";
    String Task2ToDo = "Task 2";
    int Task2DoneId = 2; // done status "uncompleted"
    int Task2CategoryBlockId = 2; // category "study"
    int Task2PriorityBlockId = 1; // priority "high"
    // Task 3
    String Task3IdOfListItem = "1701346042003";
    String Task3ToDo = "Task 3";
    int Task3DoneId = 2; // done status "uncompleted"
    int Task3CategoryBlockId = 6; // category "no category"
    int Task3PriorityBlockId = 2; // priority "medium"
    // Task 4
    String Task4IdOfListItem = "1701346042004";
    String Task4ToDo = "Task 4";
    int Task4DoneId = 1; // done status "completed"
    int Task4CategoryBlockId = 3; // category "grocery"
    int Task4PriorityBlockId = 1; // priority "high"
        // Task 5
    String Task5IdOfListItem = "1701346042005";
    String Task5ToDo = "Task 5";
    int Task5DoneId = 2; // done status "uncompleted"
    int Task5CategoryBlockId = 4; // category "sport"
    int Task5PriorityBlockId = 3; // priority "low"
        // Task 6
    String Task6IdOfListItem = "1701346042006";
    String Task6ToDo = "Task 6";
    int Task6DoneId = 1; // done status "completed"
    int Task6CategoryBlockId = 5; // category "reminder"
    int Task6PriorityBlockId = 3; // priority "low"

    // Locate elements
    Locator inputAddToDo;
    Locator buttonAddNewTask;
    Locator tasksList;
    Locator newTaskListItem;
    Locator taskListItem;

    public MainPage(Page page) {
        this.page = page;
        this.inputAddToDo = page.getByPlaceholder("Add a ToDo");
        this.buttonAddNewTask = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+"));
        this.tasksList = page.getByRole(AriaRole.LIST);
    }

    public int getDoneIdOfNewTask() {
        return doneIdOfNewTask;
    }

    public int getPriorityBlockIdOfNewTask() {
        return priorityBlockIdOfNewTask;
    }

    public int getCategoryBlockIdOfNewTask() {
        return categoryBlockIdOfNewTask;
    }

    public Locator getInputAddToDo() {
        return inputAddToDo;
    }

    public Locator getButtonAddNewTask() {
        return buttonAddNewTask;
    }

    public Locator getTasksList() {
        return tasksList;
    }

    public void addNewTask() {
        inputAddToDo.fill(newToDo);
        buttonAddNewTask.click();
    }

    public String getiIdOfNewTaskListItem() {
        newTaskListItem = tasksList.locator("li:last-child");
        idOfNewTaskListItem = newTaskListItem.getAttribute("id");
        return idOfNewTaskListItem;
    }

    public Locator getNewTaskListItem() {
        newTaskListItem = tasksList.locator("li:last-child");
        return newTaskListItem;
    }

    public Locator getTaskListItem(String id) {
        taskListItem = tasksList.locator("//li[@id='" + id + "']");
        return taskListItem;
    }

    public String getTask1IdOfListItem() {
        return Task1IdOfListItem;
    }

    public String getTask1ToDo() {
        return Task1ToDo;
    }

    public int getTask1DoneId() {
        return Task1DoneId;
    }

    public int getTask1CategoryBlockId() {
        return Task1CategoryBlockId;
    }

    public int getTask1PriorityBlockId() {
        return Task1PriorityBlockId;
    }

    public String getTask2IdOfListItem() {
        return Task2IdOfListItem;
    }

    public String getTask2ToDo() {
        return Task2ToDo;
    }

    public int getTask2DoneId() {
        return Task2DoneId;
    }

    public int getTask2CategoryBlockId() {
        return Task2CategoryBlockId;
    }

    public int getTask2PriorityBlockId() {
        return Task2PriorityBlockId;
    }

    public String getTask3IdOfListItem() {
        return Task3IdOfListItem;
    }

    public String getTask3ToDo() {
        return Task3ToDo;
    }

    public int getTask3DoneId() {
        return Task3DoneId;
    }

    public int getTask3CategoryBlockId() {
        return Task3CategoryBlockId;
    }

    public int getTask3PriorityBlockId() {
        return Task3PriorityBlockId;
    }

public String getTask4IdOfListItem() {
        return Task4IdOfListItem;
    }

    public String getTask4ToDo() {
        return Task4ToDo;
    }

    public int getTask4DoneId() {
        return Task4DoneId;
    }

    public int getTask4CategoryBlockId() {
        return Task4CategoryBlockId;
    }

    public int getTask4PriorityBlockId() {
        return Task4PriorityBlockId;
    }

public String getTask5IdOfListItem() {
        return Task5IdOfListItem;
    }

    public String getTask5ToDo() {
        return Task5ToDo;
    }

    public int getTask5DoneId() {
        return Task5DoneId;
    }

    public int getTask5CategoryBlockId() {
        return Task5CategoryBlockId;
    }

    public int getTask5PriorityBlockId() {
        return Task5PriorityBlockId;
    }

public String getTask6IdOfListItem() {
        return Task6IdOfListItem;
    }

    public String getTask6ToDo() {
        return Task6ToDo;
    }

    public int getTask6DoneId() {
        return Task6DoneId;
    }

    public int getTask6CategoryBlockId() {
        return Task6CategoryBlockId;
    }

    public int getTask6PriorityBlockId() {
        return Task6PriorityBlockId;
    }

    public int getCountTestDataTasks() {
        return countTestDataTasks;
    }

    public int getDoneStatusIdOfCompletedTask() {
        return doneStatusIdOfCompletedTask;
    }

    public int getDoneStatusIdOfUncompletedTask() {
        return doneStatusIdOfUncompletedTask;
    }

    public String getNewToDo() {
        return newToDo;
    }

    public int getCategoryId(String category) {

        if (category == "work") {
            categoryId = 1;
        } else if (category == "study") {
            categoryId = 2;
        } else if (category == "grocery") {
            categoryId = 3;
        } else if (category == "sport") {
            categoryId = 4;
        } else if (category == "other") {
            categoryId = 5;
        }
        return categoryId;
    }

    public String getCategoryName(int categoryId) {

        switch (categoryId) {
            case 1:
                categoryName = "work";
                break;
            case 2:
                categoryName = "study";
                break;
            case 3:
                categoryName = "grocery";
                break;
            case 4:
                categoryName = "sport";
                break;
            case 5:
                categoryName = "other";
                break;
        }
        return categoryName;
    }

    public int getPriorityId(String priority) {

        if (priority == "high") {
            priorityId = 1;
        } else if (priority == "medium") {
            priorityId = 2;
        } else if (priority == "low") {
            priorityId = 3;
        } else if (priority == "no_priority") {
            priorityId = 4;
        }
        return priorityId;
    }

    public String getPriorityName(int priorityId) {

        switch (priorityId) {
            case 1:
                priorityName = "high";
                break;
            case 2:
                priorityName = "medium";
                break;
            case 3:
                priorityName = "low";
                break;
            case 4:
                priorityName = "no_priority";
                break;                 
        }
        return priorityName;
    }
}

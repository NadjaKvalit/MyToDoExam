package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pagebase.PageBase;

public class MainPage extends PageBase {

    public MainPage() {
    };

    // Variables
    String newToDo = "Task 4";
      int doneIdOfNewTask = 2;
    int priorityBlockIdOfNewTask = 4;
    int categoryBlockIdOfNewTask = 6;
    String idOfNewTaskListItem;
    int doneStatusIdOfCompletedTask = 1;
    int doneStatusIdOfUncompletedTask = 2;

    //TESTDATA
    int countTestDataTasks = 3;
    //Task 1
    String Task1IdOfListItem = "1701346042001";
    String Task1ToDo = "Task 1";
    int Task1DoneId = 1; // done status "completed"
    int Task1CategoryBlockId = 1; // category "work"
    int Task1PriorityBlockId = 4; // priority "no priority"
    //Task 2
    String Task2IdOfListItem = "1701346042002";
    String Task2ToDo = "Task 2";
    int Task2DoneId = 2; // done status "uncompleted"
    int Task2CategoryBlockId = 2; // category "study"
    int Task2PriorityBlockId = 1; // priority "high"
    //Task 3
    String Task3IdOfListItem = "1701346042003";
    String Task3ToDo = "Task 3";
    int Task3DoneId = 1; // done status "completed"
    int Task3CategoryBlockId = 6; // category "no category"
    int Task3PriorityBlockId = 2; // priority "medium"

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
}

package todo_api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.options.RequestOptions;

public class POSTTask {
    public String idTasks;
    public String whatToDo;
    public int idDone;
    public int idCategory;
    public int idPriority;
    public BrowserContext context;

    public void createTask(String idTasks, String whatToDo, int idDone, int idCategory, int idPriority,
            BrowserContext context) {

        // Create Json object representing booking POST request body
        TaskDetailsJSON details = new TaskDetailsJSON(idTasks, whatToDo, idDone, idCategory, idPriority);

        // Send a Post request to db and use Json object as a body
        // setData() method automatically serializes Java object, i.e. converts it to
        // Json string
        RequestOptions body = RequestOptions.create().setData(details);
        String endpointUrl = "http://localhost:3000/tasks/";

        APIResponse apiResponse = context.request().post(endpointUrl, body);
        System.out.println(apiResponse.text());
    }

    public void updateOrInsertTask(String idTasks, String whatToDo, int idDone, int idCategory, int idPriority,
            BrowserContext context) {

        APIResponse apiResponse = context.request().get("http://localhost:3000/tasks/" + idTasks);
        int statusCode = apiResponse.status();

        if (statusCode == 404) {
            // Task doesn't exist, so create it
            createTask(idTasks, whatToDo, idDone, idCategory, idPriority, context);
        } else if (statusCode == 200) {
            // Task exists, update it
            PUTTask putTask = new PUTTask();
            putTask.updateTask(idTasks, whatToDo, idDone, idCategory, idPriority, context);
        }
    }
}
package todo_api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.options.RequestOptions;

public class PUTTask {
    public String idTasks;
    public String whatToDo;
    public int idDone;
    public int idCategory;
    public int idPriority;
    public BrowserContext context;

    public void updateTask(String idTasks, String whatToDo, int idDone, int idCategory, int idPriority, BrowserContext context) {
        // Create Json object representing PUT request body
        TaskDetailsJSON details = new TaskDetailsJSON(idTasks, whatToDo, idDone, idCategory, idPriority);

        // Send a PUT request to update the task in the database using Json object as a body
        RequestOptions body = RequestOptions.create().setData(details);
        String endpointUrl = "http://localhost:3000/tasks/" + idTasks; // Use the specific task ID in the URL

        APIResponse apiResponse = context.request().put(endpointUrl, body);
        System.out.println(apiResponse.text());
    }
}

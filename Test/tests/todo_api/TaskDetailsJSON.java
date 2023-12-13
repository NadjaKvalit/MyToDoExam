package todo_api;

public class TaskDetailsJSON {

	public String idTasks;
	public String whatToDo;
	public int Done_idDone;
	public int Category_idCategory;
	public int Priority_idPriority;
	
	public TaskDetailsJSON(String idTasks, String whatToDo, int idDone, int idCategory, int idPriority) {
		setIDTasks(idTasks);
		setWhatToDo(whatToDo);
		setIDDone(idDone);
		setIDCategory(idCategory);
		setIDPrioritys(idPriority);
	}

	public void setIDTasks(String idTasks) {
		this.idTasks = idTasks;
	}

	public void setWhatToDo(String whatToDo) {
		this.whatToDo = whatToDo;
	}

	public void setIDDone(int idDone) {
		this.Done_idDone = idDone;
	}

	public void setIDCategory(int idCategory) {
		this.Category_idCategory = idCategory;
	}

	public void setIDPrioritys(int idPriority) {
		this.Priority_idPriority = idPriority;
	}

}
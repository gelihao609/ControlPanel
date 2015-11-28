package Controller;

import java.util.*;

import Entity.Project;
import Entity.ResourcePool;
import Entity.Task;
import Entity.TaskPool;
import boundary.Oracle;
import boundary.ViewTaskWindow;

/**
 * 
 */
public class TaskControl implements Controller {

	/**
	 * Default constructor
	 */
	public TaskControl(Project p) {
		setProject(p);
		taskpool = p.getTaskPool();
		resourcePool = p.getResourcePool();
	}

	private Project project;
	private TaskPool taskpool;
	private ResourcePool resourcePool;

	@Override
	public void execute(String cmd, Oracle o) {
		if (cmd.equals("addTask")) {
			o.ask(cmd, this);
		} else if (cmd.equals("viewTask")) {
			Task result = (Task) o.ask(cmd, this);
			if (result != null) {
				Oracle viewWindow = new ViewTaskWindow(result);
				viewWindow.ask(null, this);
			}
		}

	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void addTaskToTaskPool(Task temT) {
		taskpool.add(temT);
	}

}
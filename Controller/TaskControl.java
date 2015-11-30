package Controller;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.Project;
import Entity.Task;
import Entity.TaskPool;
import boundary.DeleteTaskWindow;
import boundary.EditTaskWindow;
import boundary.Oracle;
import boundary.ViewTaskWindow;

/**
 * 
 */
public class TaskControl implements Controller {

	private Project project;
	private TaskPool taskpool;
	/**
	 * Default constructor
	 */
	public TaskControl(Project p) {
		setProject(p);
		taskpool = p.getTaskPool();
	}



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
			else JOptionPane.showMessageDialog(new JFrame("Message"), "Please select a task first.");

		} else if (cmd.equals("editTask")) {
			Task result = (Task) o.ask(cmd, this);
			if (result != null) {
				Oracle editWindow = new EditTaskWindow(result);
				editWindow.ask(null, this);
			}
			else JOptionPane.showMessageDialog(new JFrame("Message"), "Please select a task first.");
		} else if (cmd.equals("deleteTask")) {
			Task result = (Task) o.ask(cmd, this);
			if (result != null) {
				Oracle delWindow = new DeleteTaskWindow(result);
				delWindow.ask(null, this);
			}
			else JOptionPane.showMessageDialog(new JFrame("Message"), "Please select a task first.");
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
	
	public void modifyTaskInTaskPool(){
		taskpool.informObservers();
	}
	
	public void delTaskFromTaskPool(Task temT){
		taskpool.deleteTask(temT);
	}



	public void updateView() {
		
	}

}
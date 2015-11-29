package Controller;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.Project;
import Entity.Resource;
import Entity.ResourcePool;
import boundary.AddResourceWindow;
import boundary.Oracle;
import boundary.ViewResourceWindow;

/**
 * 
 */
public class ResourceControl implements Controller {
	
	private ResourcePool pool;

	/**
	 * Default constructor
	 */
	public ResourceControl() {
	}

	public ResourceControl(Project project) {
		pool = project.getResourcePool();
	}


	@Override
	public void execute(String cmd, Oracle o) {
		if (cmd.equals("addResource")) {
			o.ask(cmd, this);
		} else if (cmd.equals("viewResource")) {
			Resource result = (Resource) o.ask(cmd, this);
			if (result != null) {
				Oracle viewWindow = new ViewResourceWindow(result);
				viewWindow.ask(null, this);
			}
			else JOptionPane.showMessageDialog(new JFrame("Message"), "Please select a resource first.");
		} else if (cmd.equals("editResource")) {
			Resource result = (Resource) o.ask(cmd, this);
			if (result != null) {
				Oracle editWindow = new AddResourceWindow(result);
				editWindow.ask("editResource", this);
			}
			else JOptionPane.showMessageDialog(new JFrame("Message"), "Please select a resource first.");
		}

	}
//pool.setModified
	//
	public void modifyResourceInResourcePool(Resource tempR) {
		pool.modify(tempR);

	}
	public void addResourceToResourcePool(Resource temR) {
		pool.add(temR);

	}

}
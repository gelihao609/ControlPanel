package Controller;

import java.util.*;

import Entity.Project;
import Entity.Resource;
import Entity.ResourcePool;
import boundary.Oracle;
import boundary.ViewResourceWindow;

/**
 * 
 */
public class ResourceControl implements Controller {

    /**
     * Default constructor
     */
    public ResourceControl() {
    }
    
    public ResourceControl(Project project) {
		pool = project.getResourcePool();
	}

	private ResourcePool pool;

	@Override
	public void execute(String cmd, Oracle o) {
		if(cmd.equals("addResource"))
		{
			o.ask(cmd,this);
		}
		else if(cmd.equals("viewAssTasks"))
		{
			
			Resource result = (Resource) o.ask(cmd,this);
			if(result!=null)
			{
				Oracle viewWindow = new ViewResourceWindow(result);
				viewWindow.ask(null, this);
				
			}
		}
				else if(cmd.equals("editResource"))
		{
			o.ask(cmd,this);
		}
		
	}

	public void addResourceToResourcePool(Resource temR) {
		pool.add(temR);
		
	}


}
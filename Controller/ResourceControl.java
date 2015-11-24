package Controller;

import java.util.*;

import Entity.Project;
import Entity.Resource;
import Entity.ResourcePool;
import boundary.Oracle;

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
		
	}

	public void addResourceToResourcePool(Resource temR) {
		pool.add(temR);
		
	}


}
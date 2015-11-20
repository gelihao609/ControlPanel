package Controller;

import java.util.*;

import Entity.Project;
import boundary.Oracle;

/**
 * 
 */
public class ProjectControl implements Controller{

    /**
     * Default constructor
     */
    public ProjectControl() {
    }
    
    public ProjectControl(Project p) {
    	project = p;
    }

	public void createProject(){
    	
    }
    
    public void openProject(){
    	
    }
   public void generateShedule()
   {
	   
   }
	@Override
	public void execute(String cmd, Oracle o) {
		if(cmd.equals("createProject"))
		{
			project.setProperties((Project)o.ask(cmd));
		}
	}

	private Project project;

}
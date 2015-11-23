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
			o.ask(cmd,this);
		}
		if(cmd.equals("openProject"))
		{
			o.ask(cmd,this);
		}
		if(cmd.equals("generateSchedule"))
		{
			
		}
	}
	
	public void setProjectProperties(Project p)
	{
		project.setProperties(p);
		project.getTaskPool().addHead(p);
	}

	private Project project;

}
package Controller;

import java.util.*;

import Entity.Project;
import boundary.LoaderGateway;
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
		if(cmd.equals("saveProject"))
		{
			try {
				project.save();
			} catch (Exception e) {
				System.out.println("Error in save.");
				e.printStackTrace();
			}
		}
	}
	
	public void setProjectProperties(Project p)
	{
		project.setProperties(p);
	}

	private Project project;

}
package Controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Entity.Project;
import boundary.Oracle;

/**
 * 
 */
public class ProjectControl implements Controller{
	
	private Project project;

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
		if(cmd.equals("saveProject"))
		{
			try {
				save();
				JOptionPane.showMessageDialog(new JFrame("Succeed"), "Your project is saved.");
			} catch (Exception e) {
				System.out.println("Error in save.");
				e.printStackTrace();
			}
		}
		if(cmd.equals("openProject"))
		{	
				o.ask(cmd,this);
		}
		if(cmd.equals("closeProject"))
		{	
				clear();
		}
		
	}
	
	
	public void setProjectProperties(Project p)
	{
		project.setProperties(p);
	}
	public void save() throws Exception
	{
		project.save();
	}
	
	public void load(File selectedFile) throws SAXException, IOException, ParserConfigurationException, ParseException {
		project.load(selectedFile);		
	}
	

	public void clear() {
		project.clear();
	}


}
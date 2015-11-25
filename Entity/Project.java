package Entity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import boundary.LoaderGateway;

/**
 * 
 */
public class Project extends Element {


    
	
	/**
	 * @param name
	 */
	public Project() {
		super("untitled");
		_author="untitled";
		_company="untitled";
		_resourcePool = new ResourcePool();
		_taskPool = new TaskPool(this);
		_schedule = new Schedule(_taskPool);
		_XMLloader = new LoaderGateway();
	}



	/**
     * @param tasks
     * @param resourcePool
     * @param name
     */
    public Project(TaskPool taskPool, ResourcePool resourcePool,String name) {
    	super(name);
    	this._taskPool=taskPool;
    	this._resourcePool=resourcePool;
    	this._id = this.hashCode();
    }
    
    
    
	/**
	 * @param name
	 */
	public Project(String name) {
		super(name);
		_author="untitled";
		_company="untitled";
	}
	public Project(String name,String author, String com, Date start) {
		super(name,start);
		_author=author;
		_company=com;
	}

	public TaskPool getTaskPool() {
		return _taskPool;
	}
    
    public void addTasks(Task t) {
    	_taskPool.add(t);
	}
	public ResourcePool getResourcePool() {
		return _resourcePool;
	}
	
	public void addResource(Resource r)
	{
		_resourcePool.add(r);
	}

	public String getCompanyName() {
		return _company;
	}
	public String getStartDateInString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(_startDate);
	}

	public String getProjectName() {
		return _name;
	}



	public void setProperties(Project p) {
		_name = p.getName();
		_company = p.getCompanyName();
		_author = p.getAuthorName();
		 _startDate = p.getStartDate();
		 setChanged();
		 this.notifyObservers();
	}

	public String getAuthorName() {
		return _author;
	}
	
	public Schedule getSchedule(){
		return _schedule;
	}
	public void save() throws Exception
	{
		_XMLloader.createXML(this);
	}
	public void load(String filename) throws SAXException, IOException, ParserConfigurationException
	{
		 _XMLloader.readXML(this,filename);
	}
    private ResourcePool _resourcePool;
    private TaskPool _taskPool;
    private LoaderGateway _XMLloader;
    private String _author;
    private String _company;
    private Schedule _schedule;
	
}
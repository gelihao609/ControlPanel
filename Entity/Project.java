package Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
public class Project extends Element {


    
	
	/**
	 * @param name
	 */
	public Project() {
		super("untitled");
    	this._id = this.hashCode();
		_author="untitled";
		_company="untitled";
		_startDate=new Date();
		_resourcePool = new ResourcePool();
		_taskPool = new TaskPool();
		_schedule = new Schedule(_taskPool);
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
		_startDate=new Date();
	}
	public Project(String name,String author, String com, Date start) {
		super(name);
		_author=author;
		_company=com;
		_startDate=start;
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
		return super._name;
	}



	public void setProperties(Project p) {
		_name = p.getName();
		_company = p.getCompanyName();
		_author = p.getAuthorName();
		 _startDate = p.getStartDate();
		 setChanged();
		 this.notifyObservers();
	}



	public Date getStartDate() {
		return _startDate;
	}



	public String getAuthorName() {
		return _author;
	}
	
	public Schedule getSchedule(){
		return _schedule;
	}
	
    private ResourcePool _resourcePool;
    private TaskPool _taskPool;
    private ILoader _XMLloader;
    private String _author;
    private String _company;
    private Date _startDate;
    private Schedule _schedule;
	
}
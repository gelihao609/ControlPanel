package Entity;

import java.util.*;

/**
 * 
 */
public class Project extends Element {


    /**
     * @param tasks
     * @param resourcePool
     * @param name
     */
    public Project(List<Task> taskPool, List<Resource> resourcePool,String name) {
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
		_author=null;
		_company=null;
		_startDate=null;
	}
	public Project(String name,String author, String com, Date start) {
		super(name);
		_author=author;
		_company=com;
		_startDate=start;
	}

	public List<Task> getTaskPool() {
		return _taskPool;
	}
    
    public void addTasks(Task t) {
    	_taskPool.add(t);
	}
	public List<Resource> getResourcePool() {
		return _resourcePool;
	}
	
	public void addResource(Resource r)
	{
		_resourcePool.add(r);
	}

    private List<Resource> _resourcePool;
    private List<Task> _taskPool;
    private ILoader _XMLloader;
    private String _author;
    private String _company;
    private Date _startDate;
    private Schedule _schedule;
}
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
    public Project(Set<Task> tasks, Set<Resource> resourcePool,String name) {
    	super(name);
    	this._tasks=tasks;
    	this._resourcePool=resourcePool;
    	
    	this._id = this.hashCode();
    }
    
	/**
	 * @param name
	 */
	public Project(String name) {
		super(name);
	}

	public Set<Task> getTasks() {
		return _tasks;
	}
    
    public void addTasks(Task t) {
		_tasks.add(t);
	}
	public Set<Resource> getResourcePool() {
		return _resourcePool;
	}
	
	public void addResource(Resource r)
	{
		_resourcePool.add(r);
	}

    private Set<Task> _tasks;
    private Set<Resource> _resourcePool;


}
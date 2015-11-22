package Entity;

import java.util.*;

/**
 * 
 */
public class Task extends Element {


    /**
     * @param name
     * @param parent
     * @param children
     * @param predecessors
     * @param resources
     * @param successors
     */
    public Task(String name, Element parent, List<Task> children, List<Task> predecessors, List<Resource> resources,
    		List<Task> successors) {
		super( name,  parent,  children, resources) ;
		this._predecessors = predecessors;
		this._successors = successors;
		this._id = this.hashCode();
	}

	/**
	 * @param _name
	 * @param _id
	 */
	public Task(String name) {
		super(name);
		this._id = this.hashCode();

	}

	

	/**
	 * @param _id
	 * @param _name
	 */
	public Task(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}


	public void unassignResource()
	{
		
	}
    
    protected String _description;
    protected List<Task> _successors;
    protected List<Task> _predecessors;
    protected List<Resource> _resources;
}
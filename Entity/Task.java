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
	}


	public Task(String name, String duration, String description, Element parent) {		
		super(name,Integer.parseInt(duration),parent);
		parent.addChild(this);
		_description=description;
		_predecessors = new ArrayList<Task>();
		_resources = new ArrayList<Resource>();
	}

	@Override
	public String toString() {
		return _name;
	}

	public void unassignResource()
	{
	
	}
    
    private String _description;
    private List<Task> _successors;
    private List<Task> _predecessors;
    private List<Resource> _resources;
}
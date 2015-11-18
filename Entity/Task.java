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
    public Task(String name, Task parent, List<Task> children, List<Task> predecessors, List<Resource> resources,
    		List<Task> successors) {
		super(name);
		this._id = this.hashCode();
		this._parent = parent;
		this._children = children;
		this._predecessors = predecessors;
		this._resources = resources;
		this._successors = successors;
	}

	/**
	 * @param _name
	 * @param _id
	 */
	public Task(String name) {
		super(name);
		this._id = this.hashCode();
		this._parent = null;
		this._children = null;
		this._predecessors = null;
		this._resources = null;
		this._successors = null;
	}

	

	/**
	 * @param _id
	 * @param _name
	 */
	public Task(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public Task getParent() {
		return _parent;
	}

	public void setParent(Task parent) {
		this._parent = parent;
	}

	public List<Task> getChildren() {
		return _children;
	}

	public void setChildren(List<Task> children) {
		this._children = children;
	}

	public List<Task> getPredecessors() {
		return _predecessors;
	}

	public void setPredecessors(List<Task> predecessors) {
		this._predecessors = predecessors;
	}

	public List<Resource> getResources() {
		return _resources;
	}

	public void setResources(List<Resource> resources) {
		this._resources = resources;
	}

	public List<Task> getSuccessors() {
		return _successors;
	}

	public void setSuccessors(List<Task> successors) {
		this._successors = successors;
	}



	/**
     * 
     */
    private Task _parent;

    /**
     * 
     */
    private List<Task> _children;

    /**
     * 
     */
    private List<Task> _predecessors;

    /**
     * 
     */
    private List<Resource> _resources;

    /**
     * 
     */
    private List<Task> _successors;
    
    

}
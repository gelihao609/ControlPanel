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
    public Task(String name, Task parent, Set<Task> children, Set<Task> predecessors, Set<Resource> resources,
			Set<Task> successors) {
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

	public Set<Task> getChildren() {
		return _children;
	}

	public void setChildren(Set<Task> children) {
		this._children = children;
	}

	public Set<Task> getPredecessors() {
		return _predecessors;
	}

	public void setPredecessors(Set<Task> predecessors) {
		this._predecessors = predecessors;
	}

	public Set<Resource> getResources() {
		return _resources;
	}

	public void setResources(Set<Resource> resources) {
		this._resources = resources;
	}

	public Set<Task> getSuccessors() {
		return _successors;
	}

	public void setSuccessors(Set<Task> successors) {
		this._successors = successors;
	}



	/**
     * 
     */
    private Task _parent;

    /**
     * 
     */
    private Set<Task> _children;

    /**
     * 
     */
    private Set<Task> _predecessors;

    /**
     * 
     */
    private Set<Resource> _resources;

    /**
     * 
     */
    private Set<Task> _successors;
    
    

}
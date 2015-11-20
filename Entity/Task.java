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
    public Task(String name, Task parent, List<Element> children, List<Element> predecessors, List<Resource> resources,
    		List<Element> successors) {
		super( name,  parent,  children,  predecessors, resources,
	    		 successors) ;
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
    

}
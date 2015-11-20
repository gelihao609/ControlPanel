package Entity;

import java.util.*;

/**
 * 
 */
public abstract class Element {

    /**
     * Default constructor
     */
    /**
     * @param name
     */
    public Element(String name) {
    	this._name = name;
    }
    
    public Element(String name, Task parent, List<Element> children, List<Element> predecessors, List<Resource> resources,
    		List<Element> successors) {
    	this._name = name;
    	this._id = this.hashCode();
		this._parent = parent;
		this._children = children;
		this._predecessors = predecessors;
		this._resources = resources;
		this._successors = successors;
	}

    /**
	 * @param _id
	 * @param _name
	 */
	public Element(int _id, String _name) {
		super();
		this._id = _id;
		this._name = _name;
	}

	/**
     * 
     */

    public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	/**
     * 
     */
    protected int _id;
    protected String _name;
    protected String _description;
    protected int _duration;
    protected Date _startDate;
    protected Date _endDate;
    protected double _percentageCompleted;
    
    protected List<Element> _successors;
    protected List<Element> _predecessors;
    protected List<Element> _children;
    protected List<Resource> _resources;
    protected Element _parent;





}
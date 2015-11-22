package Entity;

import java.util.*;

/**
 * 
 */
public abstract class Element extends Observable {

    /**
     * Default constructor
     */
	public Element(){
		_name="untitled";
	}
    /**
     * @param name
     */
    public Element(String name) {
    	this._name = name;
    }
    
    public Element(String name, Element parent, List<Task> children, List<Resource> resources) {
    	this._name = name;
    	this._id = this.hashCode();
		this._parent = parent;
		this._children = children;
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
    protected int _duration;
    protected Date _startDate;
    protected Date _endDate;
    protected double _percentageCompleted;
    

    protected List<Task> _children;
    protected Element _parent;





}
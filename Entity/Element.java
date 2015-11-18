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
    
    protected List<Task> _successors;
    protected List<Task> _predecessors;
    protected List<Task> _children;
    protected Task _parent;





}
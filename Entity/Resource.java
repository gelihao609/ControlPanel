package Entity;

import java.util.*;

/**
 * 
 */
public class Resource {

	/**
     * Default constructor
     */
    public Resource() {
    }
    
    /**
	 * @param _id
	 * @param name
	 */
	public Resource(String name) {
		this._id = this.hashCode();
		this._name = name;
	}

    public Resource(String name, String rate, String type) {
    	this._id = this.hashCode();
    	this._name = name;
    	this._rate = rate;
    	this._type = type;
    	_references = new ArrayList<Task>();
	}

	public Resource(String name, String type, String rate, String id) {
    	this._id = Integer.parseInt(id);
    	this._name = name;
    	this._rate = rate;
    	this._type = type;
    	_references = new ArrayList<Task>();
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		this._name = name;
	}
	
	public void setRate(String rate) {
		this._rate = rate;
	}
	
	public void setType(String type) {
		this._type = type;
	}
	public void setID(int id) {
		this._id = id;
	}
	
	
	private int _id;
	private String _name;
	//-- Is these attributes necessary?
	private Date _startDate;
	private Date _endDate;
	//--------------------//
	private String _type;
	private String _rate;
	private List<Task> _references;
	public String getType() {
		return _type;
	}

	public String getCost() {
		return _rate;
	}
	
	public String toString()
	{
		return _name;
	}

	public void addReference(Task task) {
		_references.add(task);
		
	}

	public List<Task> getReferencedTasks() {
		// TODO Auto-generated method stub
		return _references;
	}

}
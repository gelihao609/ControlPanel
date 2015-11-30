package Entity;

import java.util.*;

/**
 * 
 */
public class Resource {
	
	private int _id;
	private String _name;
	private String _type;
	private String _rate;
	private List<Task> _references;

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
		HashSet<Task> referenceSet = new HashSet<Task>(_references);
		if(!referenceSet.contains(task))_references.add(task);
	}

	public void removeTaskReference(Task task) {
		_references.remove(task);
	}
	
	public List<Task> getReferencedTasks() {
//		return _references!=null?(List<Task>) _references:null;
		return _references;
	}

}
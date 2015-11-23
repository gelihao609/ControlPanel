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
		super();
		this._id = this.hashCode();
		this._name = name;
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

	private int _id;
	private String _name;
	//-- Is these attributes necessary?
	private String _type;
	private Date _startDate;
	private Date _endDate;
	//--------------------//
	private String _rate;
	private List<Task> _references;
}
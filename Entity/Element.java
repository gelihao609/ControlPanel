package Entity;

import java.util.*;

public abstract class Element extends Observable {
    /**
     * Default constructor
     */
	public Element(){
		_name="";
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

	public Element(String name) {
    	this._name = name;
    	this._id = this.hashCode();
    	this._children = new ArrayList<Task>();
    	this._startDate = new Date();
    	this._duration = -1;
	}

	public Element(String name, int duration, Element parent) {
    	this._name = name;
    	this._id = this.hashCode();
        _duration = duration;
        _parent = parent;
        //This used when adding a parent
        _children = new ArrayList<Task>();
        _parent.addChild((Task)this);
        }
	
	public Element(String name, Date start) {
    	this._name = name;
    	this._id = this.hashCode();
    	this._children = new ArrayList<Task>();
    	this._startDate = start;
    	this._duration = -1;//default value, means duration not set
	}
	
	public void removeChild(Task current) {
		_children.remove(current);
	}

	public Element(String name, int duration, Element parent, int id) {
    	this._name = name;
    	this._id = id;
        _duration = duration;
        _parent = parent;
        //This used when adding a parent
        _children = new ArrayList<Task>();
		parent.addChild((Task)this);
	}

    public int getId() {
		return _id;
	}
 
	public String getName() {
		return _name;
	}
	//set start date also set end date
	public void setStartDate(Date d){
		this._startDate = d;
		if(d!=null){
		Date endDate = new Date();
		endDate.setTime(d.getTime() + _duration*Utility.MILLISECONDS_PER_DAY);
		setEndDate(endDate); 
		}
	}

	void setEndDate(Date d) {
		this._endDate=d;
		if(d!=null)
		{
		//if duration is not set, especially for project
		if(this._duration==-1)
		{
			_duration = (int) Math.ceil(((_endDate.getTime()-_startDate.getTime())/Utility.MILLISECONDS_PER_DAY));
		}
		}
	}

	public void setName(String name) {
		this._name = name;
	}
	
	public ArrayList<Task> getChildren()
	{
		return _children!=null?(ArrayList<Task>) _children:null;
	}
	
	public Element getParent() {
		return _parent;
	}
	
	public String getDuration() {
		return Integer.toString(_duration);
	}
	
	public void setParent(Element e)
	{
		_parent = e;
		e.addChild((Task)this);
	}
	
	protected void addChild(Task task) {
		_children.add(task);
	}
	public Date getStartDate() {
		return _startDate;
	}
	public Date getEndDate() {
		return _endDate;
	}
	public void setDuration(int time){
		_duration = time;
	}
	
	public void setID(String id){
		_id = Integer.parseInt(id);
	}

	public void clear() {
		_id= new Project().hashCode();
		_name = "";
		_startDate = new Date();
		_endDate = null;
		_children.clear();
		_duration = -1;
	}
	
    protected int _id;
    protected String _name;
    protected int _duration;
    protected Date _startDate;
    protected Date _endDate;
    protected double _percentageCompleted;
    protected List<Task> _children;
    protected Element _parent;
    

}
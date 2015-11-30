package Entity;

import java.util.*;

public class Task extends Element {
	 
    private String _description;
    private List<Task> _successors;
    private List<Task> _predecessors;
    private List<Resource> _resources;
    /* do not allow following relations:
     * O 
     * |\
     * | \
     * |  O
     * \  |
     * 	\ |
     * 	 \|
     * 	  O
     */
    
    /************Constructors*******************/
    /**
     * 
     * @param name
     * @param parent
     * @param children
     * @param predecessors
     * @param resources
     * @param successors
     */
    public Task(String name, Element parent, List<Task> children, List<Task> predecessors, List<Resource> resources,
    		List<Task> successors) {
		super( name,  parent,  children, resources) ;
		this._predecessors = predecessors;
		this._successors = successors;
	}
    /**
     * 
     * @param name
     */
    public Task(String name) {
		super(name);
	}
	/**
	 * @param _id
	 * @param _name
	 */
	public Task(int id, String name) {
		super(id, name);
	}
	
	//for load usage
	/**
	 * 
	 * @param name
	 * @param duration
	 * @param description
	 * @param parent
	 * @param id
	 */
	public Task(String name, String duration, String description, Element parent,String id) {		
		super(name,Integer.parseInt(duration),parent,Integer.parseInt(id));
		_description=description;
		_predecessors = new ArrayList<Task>();
		_successors = new ArrayList<Task>();
		_resources = new ArrayList<Resource>();
	}
	/**
	 * 
	 * @param name
	 * @param duration
	 * @param description
	 * @param pred
	 * @param assignedResource
	 * @param parent
	 */
	public Task(String name, String duration, String description, ArrayList<Task> pred,
			ArrayList<Resource> assignedResource, Project parent) {
		super(name,Integer.parseInt(duration),parent);
		_description=description;
		_predecessors = pred;
		_successors = new ArrayList<Task>();
		linkSelfAsSuccessor(pred);
		_resources = assignedResource;
		linkTaskAsReferenceForResource(assignedResource);		
	}
	/**
	 * 
	 * @param name
	 * @param duration
	 * @param description
	 * @param pred
	 * @param assignedResource
	 * @param children
	 * @param parent
	 */
	//constructor with children defined
	public Task(String name, String duration, String description, ArrayList<Task> pred,
			ArrayList<Resource> assignedResource, ArrayList<Task> children, Project parent) {
		super(name,Integer.parseInt(duration),parent,children);
		_description=description;
		_predecessors = pred;
		_successors = new ArrayList<Task>();
		linkSelfAsSuccessor(pred);
		_resources = assignedResource;
		linkTaskAsReferenceForResource(assignedResource);
	}
	
	
	/************public methods***********/

	public void unassignResource(Resource r)//added input argument
	{
		_resources.remove(r);
	}
    
	public String getDescr() {
		return _description;
	}

	public ArrayList<Task> getPredecessor() {
		return _predecessors!=null?(ArrayList<Task>) _predecessors:null;
	}
	
	public ArrayList<Task> getSuccessor() {
		return _successors!=null?(ArrayList<Task>) _successors:null;
	}

	public ArrayList<Resource> getResource() {
		return _resources!=null?(ArrayList<Resource>) _resources:null;
	}
	
	public void addPredecessor(Task t)
	 {
		HashSet<Task> predecessorSet = new  HashSet<Task>(_predecessors);
		if(!predecessorSet.contains(t))_predecessors.add(t);
		t.addSucessor(this);
	 }
	 
	public void addAssignedResource(Resource r)
	 {
		HashSet<Resource> resourceSet = new  HashSet<Resource>(_resources);
		if(!resourceSet.contains(r))_resources.add(r);
		 r.addReference(this);
	 }

	public ArrayList<long[]> getAsgndRsreNonAvlbPrid() {
		ArrayList<long[]> slots = new ArrayList<long[]>();
		if(_resources==null) _resources = new ArrayList<Resource>();
		for(Resource r : _resources)
			{
				for(Task t : r.getReferencedTasks())
				{
					 long[] slot = new long[2];
					 //find out tasks with assigned date
					if(t.getStartDate()!=null)
					{
						//put start time and end time
						slot[0]=t.getStartDate().getTime();
						slot[1]=t.getEndDate().getTime();
						slots.add(slot);
					}
				}
			}
			slots.sort(new Comparator<long[]>(){
				@Override
				public int compare(long[] before, long[] after) {
					int result = 0;
					if(before[0]<after[0]) result = -1;
					else if(before[0]==after[0]) result = 0;
					else result = 1;
					return result;
				}});
			return slots;
		}

	public Date getPredLatestEndDate() {
			long latestDate = 0;
			for(Task t:_predecessors)
			{
				if(t.getEndDate().getTime()>latestDate)latestDate = t.getEndDate().getTime();
			}
			Date result = new Date();
			result.setTime(latestDate);
			return result;
		}

	public List<Task> getSuccessors() {
			return _successors;
		}
	//traverse to find all the predecessors and successors of a task and put result in a hashSet
	public void collectAllPreSucc(HashSet<Task> taskContainer) {
		if(_predecessors.size()!=0)
		{
			for(Task t:_predecessors)
			{
				taskContainer.add(t);
				if(t.getPredecessor().size()!=0)t.collectAllPreSucc(taskContainer);
			}
		}
		if(_successors.size()!=0)
		{
			for(Task t:_successors)
			{
				taskContainer.add(t);
				if(t.getSuccessor().size()!=0)t.collectAllPreSucc(taskContainer);
			}
		}

	}
	
	/************Private methods***********/
	private void linkSelfAsSuccessor(List<Task> predecessors) {
		for(int i=0;i<predecessors.size();i++)
		{
			predecessors.get(i).addSucessor(this);
		}		
	}

	private void addSucessor(Task task) {
		HashSet<Task> successorSet = new HashSet<Task>(_successors);
		if(!successorSet.contains(task))_successors.add(task);		
	}

	private void linkTaskAsReferenceForResource(ArrayList<Resource> assignedResource) {
		for(int i=0;i<assignedResource.size();i++)
		{
			assignedResource.get(i).addReference(this);
		}			
	}

	@Override
	public String toString() {
		return _name;
	}
	public void removePredecessor(Task taskToRemove) {
		_predecessors.remove(taskToRemove);
		taskToRemove.removeSuccessor(this);
	}
	private void removeSuccessor(Task task) {
		_successors.remove(task);
	}
}
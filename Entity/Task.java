package Entity;

import java.util.*;

public class Task extends Element {
    /**
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

	private void linkSelfAsSuccessor(List<Task> predecessors) {
		for(int i=0;i<predecessors.size();i++)
		{
			predecessors.get(i).addSucessor(this);
		}		
	}

	private void addSucessor(Task task) {
		_successors.add(task);		
	}

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
	public Task(String name, String duration, String description, Element parent,String id) {		
		super(name,Integer.parseInt(duration),parent,Integer.parseInt(id));
		_description=description;
		_predecessors = new ArrayList<Task>();
		_successors = new ArrayList<Task>();
		_resources = new ArrayList<Resource>();
	}

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

	public void unassignResource()
	{
	}
    
	public String getDescr() {
		return _description;
	}

	public ArrayList<Task> getPredecessor() {
		return (ArrayList<Task>) _predecessors;
	}

	public ArrayList<Resource> getResource() {
		return (ArrayList<Resource>) _resources;
	}
	
	 public void addPredecessor(Task t)
	 {
		 _predecessors.add(t);
		 t.addSucessor(this);
	 }
	 
	 public void addAssignedResource(Resource r)
	 {
		 _resources.add(r);
		 r.addReference(this);
	 }
	    
	    private String _description;
	    private List<Task> _successors;
	    private List<Task> _predecessors;
	    private List<Resource> _resources;

}
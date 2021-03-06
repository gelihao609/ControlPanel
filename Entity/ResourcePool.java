package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ResourcePool extends Observable {
private List<Resource> resources;
public ResourcePool()
{
	resources = new ArrayList<Resource>();
}
public ResourcePool(List<Resource> rs) {
	resources = rs;
}

public void setProperties(Object result)
{
	
}
//controller pass task to the reference of a resource
public void setTaskReference(Resource res, Element t)
{
	
}
public List<Element> checkResourceAssociation(Resource res)
{
	return null;
}
public void  deleteResource(Resource res)
{
	
}
public void removeTaskAssociation(List<Element> tasks, Resource res)
{
	
}
public void removeTaskReference(Resource res, Element t){
}
public void add(Resource r) {
	resources.add(r);
	setChanged();
	notifyObservers(this);
}
public void delete(Resource r) {
	resources.remove(r);
	setChanged();
	notifyObservers(this);
}
public void modify(Resource r) {
	for(int i=0;i<resources.size();i++)
	{
		if(r.getId() == get(i).getId()){
			this.get(i).setName(r.getName());
			this.get(i).setRate(r.getCost());
			this.get(i).setType(r.getType());
		}
	}
	setChanged();
	notifyObservers(this);
	
//	for(int i = 0; i < resources.size(); i++) {   
//	    System.out.print(resources.get(i));
//	} 
}
public int size() {
	return resources.size();
	
}
public Resource get(int i) {
	return resources.get(i);
}
public void clear() {
	resources.clear();
	setChanged();
	notifyObservers(this);
}
//public setModifiedProps(Resource resource)
//{
//	//traverse thru list resources in pool and find a match with resource id of addResWindow
//}
}

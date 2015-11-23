package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class TaskPool extends Observable{
	
private List<Task> pool;
private Project head;
public TaskPool(){
	pool = new ArrayList<Task>();
}
public TaskPool(List<Task> taskPoolSet) {
	pool = taskPoolSet;
}
public void addTask(Object result){
	
}

public void setProperties(Object result){
	
}

public void addResourceToTask(Element task, Resource res)
{
	
}
public void deleteTask(Task t)
{
	
}
public void add(Task t) {
	pool.add(t);
	setChanged();
	notifyObservers(head);
}
public int size()
{
	return pool.size();
}
public Iterator<Task> iterator() {
	// TODO Auto-generated method stub
	return pool.iterator();
}
//Head is main Task, which is Project
public Project getHead()
{
	return head;
}
public void addHead(Project p) {
	head =p;
	
}
}

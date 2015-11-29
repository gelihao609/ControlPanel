package Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class TaskPool extends Observable{
	
private List<Task> pool;
private Project head;
public TaskPool(Project head){
	pool = new ArrayList<Task>();
	this.head = head;
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
	notifyObservers();
}
public int size()
{
	return pool.size();
}
public Iterator<Task> iterator() {
	return pool.iterator();
}
public String[] getTaskNameToArray()
{
	String[] s=new String[pool.size()];
	for(int i=0;i<pool.size();i++)
	{
		s[i]=pool.get(i).getName();
	}
	return s;
}
//Head is main Task, which is Project
public Project getHead()
{
	return head;
}

public Task get(int index) {
	
	return pool.get(index);
}
public void clear() {
	pool.clear();
	setChanged();
	notifyObservers("clear");
}
public void sortStartDateASC(ArrayList<Task> list) {
	list.sort(new Comparator<Task>()
			{
				@Override
				public int compare(Task current, Task after) {
					if(current.getStartDate().before(after.getStartDate())) return -1;
					else if (current.getStartDate().after(after.getStartDate())) return 1;
					else return 0;
				}
			});
}
public void resetTaskDates() {
	//reset tasks start/end date null
	for(Task t:pool)
	{
			t.setStartDate(null);
			t.setEndDate(null);
			if(t.getChildren().size()!=0)
			{
				t.setDuration(-1);//reset composite task duration to -1
			}
	}	
}
public void update(Task temT) {
	// TODO Auto-generated method stub
	
}


}

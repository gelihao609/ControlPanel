package Entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import boundary.XLSLoader;

public class Schedule extends Observable{
	private TaskPool pool;
	private String[][] table;
	private ILoader xlsLoader;
	
	public Schedule(TaskPool p){
		pool=p;
		xlsLoader = new XLSLoader();
	}
	//Set Task Start/End Date and Project End Date (No consideration of childTask)
	private void setTaskStartDate(){
		ArrayList<Task> tasksToSchedule = (ArrayList<Task>) pool.getHead()._children;
		//BFS search
		Queue<Task> taskQueue = new LinkedList<Task>();
		//find tasks without predecessors
		for(int i=0;i<tasksToSchedule.size();i++)
		{
			Task current = tasksToSchedule.get(i);
			//find not-assigned-date tasks
			if(current.getStartDate()==null)
			{
				//find tasks with no predecessors
				if(current.getPredecessor().size()==0)
				{
					//find date when all assigned resources are available during current task period 
					current.setStartDate(getEaliestPossibleDate(current,current._parent.getStartDate()));
					//enqueue
					taskQueue.offer(current);
				}
			}
		}
		//iterate
		while(taskQueue.peek() != null)
		{
			Task current=taskQueue.poll();
			//find child nodes
			ArrayList<Task> succe = (ArrayList<Task>) current.getSuccessors();
			if(succe.size()!=0)
			{
				for(Task t:succe)
				{	
					if(t.getStartDate()!=null) System.out.println("Something is wrong in bfs");
					else{
						//find latest predecessor's end date + 1 as tentative start date
						long tentativeTime=t.getPredLatestEndDate().getTime()+Utility.MILLISECONDS_PER_DAY;
						Date tentativeStartDate = new Date();
						tentativeStartDate.setTime(tentativeTime);
						//find date when all assigned resources are available during current task period 
						t.setStartDate(getEaliestPossibleDate(t,tentativeStartDate));
						taskQueue.offer(t);
					}
				}
			}
			//after set all successor's date of current
		}
		//set Project EndDate by iterating to find latest endDate
		setProjectEndDate(tasksToSchedule);

		
	}
	
	private void setProjectEndDate(ArrayList<Task> tasksToSchedule) {
		if(tasksToSchedule.size()==0)System.out.println("Something is wrong after bfs");
		Date projectEndDate = tasksToSchedule.get(0).getEndDate();//pre-set the first task's endDate as project endDate
		for(Task t:tasksToSchedule){			
			if(t.getEndDate().after(projectEndDate))projectEndDate = t.getEndDate();
		}
		pool.getHead().setEndDate(projectEndDate);
	}
	//with tentative date from predecessors or project, get final date
	private Date getEaliestPossibleDate(Task current, Date tentativeTime) {
		int duration = Integer.parseInt(current.getDuration());
		ArrayList<long[]> nonAvlbSlot = current.getAsgndRsreNonAvlbPrid();
		//if nonAvlSlot has no value or if tentative endTime is before start time of nonAvlbSlot
		if(nonAvlbSlot.size()==0 ||
				tentativeTime.getTime()+duration*Utility.MILLISECONDS_PER_DAY<nonAvlbSlot.get(0)[0])
			return tentativeTime;
		Date resultDate = null;
		for(int i=0; i<nonAvlbSlot.size()-1;i++)
		{
			if(nonAvlbSlot.get(i+1)==null) break;
			//find gap between two slots
			if(nonAvlbSlot.get(i+1)[0]-nonAvlbSlot.get(i)[1]>=duration*Utility.MILLISECONDS_PER_DAY)
			{
				resultDate=new Date();
				resultDate.setTime(nonAvlbSlot.get(i)[1]+Utility.MILLISECONDS_PER_DAY);
				break;
			}
		}
		//could not find gap between two slots
		if(resultDate==null) 
		{
			resultDate = new Date();
			resultDate.setTime(nonAvlbSlot.get(nonAvlbSlot.size()-1)[1]+Utility.MILLISECONDS_PER_DAY);
		}
		return resultDate;
	}

	public Schedule() {
	}
	
	public void makeScheduleAsStringArray() {
		ArrayList<String[]> scheduleTable = new ArrayList<String[]>();
		ArrayList<Task> TaskToSchedule = (ArrayList<Task>) pool.getHead()._children;
		if(TaskToSchedule.size()!=0)
		{
		setTaskStartDate();
		pool.sortStartDateASC(TaskToSchedule);
		int days = Integer.parseInt(pool.getHead().getDuration());
		long startTime = pool.getHead().getStartDate().getTime();
		for(int i=0;i<days;i++)
		{
			String[] row = new String[2];
			row[0]=Integer.toString(i);
			row[1]="";
			for(Task t:TaskToSchedule)
			{
				//for each task, if current Date is after its startDate and before its endDate then ad into row
				if(t.getStartDate().getTime()<=startTime+i*Utility.MILLISECONDS_PER_DAY&&
						t.getEndDate().getTime()>=startTime+i*Utility.MILLISECONDS_PER_DAY)
					row[1]+=t+" ";
			}
			scheduleTable.add(row);
		}
		table = new String[scheduleTable.size()][2];
		for(int i=0;i<scheduleTable.size();i++)
		{
			table[i][0] = scheduleTable.get(i)[0];
			table[i][1] = scheduleTable.get(i)[1];
		}
	}
		else table = null;		
		setChanged();
		notifyObservers();
	}
	
	public TaskPool getPool(){
		return pool;
	}
	public String[][] getScheduleTable() {
		return table;
	}
	public void clear() {
		table = null;
		setChanged();
		notifyObservers();
	}
	public void export() throws FileNotFoundException, IOException {
		((XLSLoader)xlsLoader).export(Utility.makeFile(pool.getHead(),xlsLoader),"schedule",table);
		
	}
	
	
}

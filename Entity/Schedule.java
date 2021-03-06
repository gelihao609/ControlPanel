package Entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	//Set Task Start/End Date and Project End Date (No consideration of 2 level children)
	private void setTaskStartDate(){
		setTaskStartDateForHeadTasks(pool.getHead(),"",null);
	}
	
	private void setTaskStartDateForHeadTasks(Element projectOrTask, String message, Date tentative) {
			ArrayList<Task> tasksToSchedule = projectOrTask.getChildren();
				//BFS search
				Queue<Task> taskQueue = new LinkedList<Task>();
				//find tasks without predecessors, use parent's date as tentative start date
				for(int i=0;i<tasksToSchedule.size();i++)
				{
					Task current = tasksToSchedule.get(i);
					//find not-assigned-date tasks
					if(current.getStartDate()==null)
					{
						//find tasks with no predecessors
						if(current.getPredecessor().size()==0)
						{
							//simple task find date when all assigned resources are available during current task period
							if(current.getChildren().size()==0)
							{
								//composite task's subtasks
								if(message.equals("inner"))
								{
									current.setStartDate(getEaliestDateFromSrc(current,tentative));
								}
									//set start date for non-successor simple task
									else 
										{
											current.setStartDate(getEaliestDateFromSrc(current,current.getParent().getStartDate()));
										}	
							}
							else
							{	//this is for a composite task to set its start date
								setTaskStartDateForHeadTasks(current, "inner", current.getParent().getStartDate());
							}
							//enqueue
						taskQueue.offer(current);
						}
					}
				}

				//select start date for composite task 
				if(message.equals("inner"))
				{
					if(projectOrTask.getStartDate()!=null) System.out.print("Something wrong in successor composite task");
					else
					{	
						Date startDateForComposite=tasksToSchedule.get(0).getStartDate();//temporary value
						for(Task t: tasksToSchedule)
						{
							if(t.getStartDate()==null)System.out.print("Something wrong in successor composite task");
							else if(t.getStartDate().before(startDateForComposite))startDateForComposite=t.getStartDate();
						}
						projectOrTask.setStartDate(startDateForComposite);
					}
				}
				//iterate from headTasks
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
								//find latest predecessor's end date as tentative start date
								long tentativeTime=t.getPredLatestEndDate().getTime();
								Date tentativeStartDate = new Date();
								tentativeStartDate.setTime(tentativeTime);
								//Simple task: find date when all assigned resources are available during current task period 
								if(t.getChildren().size()==0)
									t.setStartDate(getEaliestDateFromSrc(t,tentativeStartDate));
								//Lv1 Composite task: 
								//1. get tasks without predecessors and get their start date
								else
									setTaskStartDateForHeadTasks(t,"inner",tentativeStartDate);
								
								taskQueue.offer(t);//enqueue
								
							}
						}
					}
					//after set all successor's date of current
				}
				//set Project EndDate by iterating to find latest endDate
					setElementEndDate(tasksToSchedule,projectOrTask);	

	}
	//when the parent is not project, it needs to find out the start date
	private Date findAnSetStartDate(Element current) {
		Date result = null;
		if(current.getStartDate()==null) result = findAnSetStartDate(current.getParent());
		else{result = current.getStartDate();}
		return result;
	}
	//for project and composite task
	private void setElementEndDate(ArrayList<Task> tasksToSchedule, Element needDateElement) {
		if(tasksToSchedule.size()==0)System.out.println("Something is wrong after bfs");
		Date elementEndDate = tasksToSchedule.get(0).getEndDate();//pre-set the first task's endDate as project endDate
		for(Task t:tasksToSchedule){			
			if(t.getEndDate().after(elementEndDate))elementEndDate = t.getEndDate();
		}
		needDateElement.setEndDate(elementEndDate);
	}
	
	//with tentative date from predecessors or project, get final date
	private Date getEaliestDateFromSrc(Task current, Date tentativeTime) {
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
				//resultDate.setTime(nonAvlbSlot.get(i)[1]+Utility.MILLISECONDS_PER_DAY);
				resultDate.setTime(nonAvlbSlot.get(i)[1]);
				break;
			}
		}
		//could not find gap between two slots
		if(resultDate==null) 
		{
			resultDate = new Date();
			//resultDate.setTime(nonAvlbSlot.get(nonAvlbSlot.size()-1)[1]+Utility.MILLISECONDS_PER_DAY);
			resultDate.setTime(nonAvlbSlot.get(nonAvlbSlot.size()-1)[1]);

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
						t.getEndDate().getTime()>startTime+i*Utility.MILLISECONDS_PER_DAY)
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
		setAbsoluteDate(table);
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
	public void export() throws FileNotFoundException, IOException, NullPointerException {
		xlsLoader.save(Utility.makeFile(pool.getHead(),xlsLoader), table);
		//((XLSLoader)xlsLoader).export(Utility.makeFile(pool.getHead(),xlsLoader),"schedule",table);
		
	}
	public void resetTaskDatesNDuration() {
		table = null;
		pool.resetTaskDates();
		//set project duration -1,which means unknown
		pool.getHead().setDuration(-1);		
	}
	//give date as exact date and bypass holidays
	private void setAbsoluteDate(String[][] tableModel){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start = pool.getHead().getStartDate();
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(start);
		for(String[] t:tableModel)
		{
			int numInAWeek = currentCal.get(Calendar.DAY_OF_WEEK);
			//Sunday
			if(numInAWeek==1)
			{
				currentCal.add(Calendar.DATE, 1);
			}
			if(numInAWeek==7)
			{
				currentCal.add(Calendar.DATE, 2);
			}
			t[0] = dateFormat.format(currentCal.getTime());
			//increment by 1
			currentCal.add(Calendar.DATE, 1);
		}
	}
	
	
}

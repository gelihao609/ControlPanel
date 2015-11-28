package boundary;

import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entity.Element;
import Entity.Project;
import Entity.Schedule;
import Entity.TaskPool;

/**
 * 
 */
public class ScheduleView extends JTable implements View {

    /**
     * Default constructor
     * @param sc 
     */
    public ScheduleView(Schedule sc){
    	init(sc);
    	schedule.addObserver(this);
    }
    
    private void init(Schedule sc){
    	schedule = sc;
		 setModel(new DefaultTableModel(
			new String[][] {
				{"0", "Task1"},
				{"1", "Task2"},
				{"2", "Taks2,Task3"}},new String[] {"Day", "Tasks"}));
		 	 
		 	TaskPool tasks = sc.getTaskPool();
		 	Project mainTask = tasks.getHead();
		 	int depth=findDepth(mainTask);
		 	String[][] table = new String[depth][2];
		 	for(int i=0;i<mainTask.getChildren().size();i++)
		 	{
		 		if(i==0)//first task is 0 
		 			{
		 				table[i][0]="0";
		 				//table[][]
		 			}
		 		else
		 		{
		 			
		 		}
		 	}
    }


    private int findDepth(Element mainTask) {
			
		return 0;
	}


	/**
     * 
     */
    private Schedule schedule;


	@Override
	public void update(Observable o, Object arg) {
		init((Schedule)arg);
	}
}
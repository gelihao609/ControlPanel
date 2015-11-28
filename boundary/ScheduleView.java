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
    	String[][] model = sc.getScheduleTable();
	 	if(model==null) model = new String[][]{{"N/A","N/A"}};
		 setModel(new DefaultTableModel(		
			model,new String[] {"Date", "Tasks"}));
    }
	/**
     * 
     */
    private Schedule schedule;
    
	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass()==Schedule.class)init((Schedule)o);//notified by Schedule
	}
}
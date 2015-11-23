package boundary;

import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entity.Project;
import Entity.Schedule;

/**
 * 
 */
public class ScheduleView extends JTable implements View {

    /**
     * Default constructor
     * @param sc 
     */
    public ScheduleView(Schedule sc){
    	schedule = sc;
		 setModel(new DefaultTableModel(
			new String[][] {
				{"0", "Task1"},
				{"1", "Task2"},
				{"2", "Taks2,Task3"}},new String[] {"Day", "Tasks"}));
		 //getColumnModel().getColumn(0).setPreferredWidth(15);
		 getColumnModel().getColumn(0).setMaxWidth(150);
		 getColumnModel().getColumn(1).setMaxWidth(150);
		 //getColumnModel().getColumn(1).setPreferredWidth(45);
    }


    /**
     * 
     */
    private Schedule schedule;


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
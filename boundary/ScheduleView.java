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
     */
    public ScheduleView() {
    }


    /**
     * 
     */
    private Schedule schedule;


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public void setModel(DefaultTableModel defaultTableModel) {
		// TODO Auto-generated method stub
		
	}

}
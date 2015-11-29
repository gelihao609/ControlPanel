package Controller;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.Project;
import Entity.Schedule;
import boundary.Oracle;

/**
 * 
 */
public class ScheduleControl implements Controller{

	private Schedule schedule;

    /**
     * Default constructor
     */
    public ScheduleControl() {
    }
    
    public ScheduleControl(Project project) {
		this.schedule=project.getSchedule();
	}


	@Override
	public void execute(String cmd, Oracle o) {
			if(cmd.equals("generateSchedule")){
				schedule.resetTaskDatesNDuration();
				schedule.makeScheduleAsStringArray();
			}
			if(cmd.equals("exportSchedule"))
			{
				export();
			}
	}

	private void export() {
		try {
			schedule.export();
			JOptionPane.showMessageDialog(new JFrame("Succeed"), "Schedule is exported in PCP outputs.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Export Schedule fails. Try again.");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "No generated schedule available. Please first generate schedule.");
			e.printStackTrace();
		}  
	}
}
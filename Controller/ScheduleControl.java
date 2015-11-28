package Controller;

import java.util.*;

import Entity.Project;
import Entity.Schedule;
import boundary.Oracle;

/**
 * 
 */
public class ScheduleControl implements Controller{

    /**
     * Default constructor
     */
    public ScheduleControl() {
    }
    
    public ScheduleControl(Project project) {
		this.schedule=project.getSchedule();
	}

	private Schedule schedule;

	@Override
	public void execute(String cmd, Oracle o) {
			if(cmd.equals("generateSchedule")){
				schedule.makeScheduleAsStringArray();
			}
	}

}
package Controller;

import java.util.*;

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
    
    public ScheduleControl(Schedule schedule) {
		this.schedule=schedule;
	}

	private Schedule schedule;

	@Override
	public void execute(String cmd, Oracle o) {
			
	}

}
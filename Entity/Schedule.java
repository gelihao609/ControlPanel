package Entity;

import java.util.Observable;

public class Schedule extends Observable{
	private TaskPool pool;
	
	public Schedule(TaskPool p){
		pool=p;
	}

	public Schedule() {
		// TODO Auto-generated constructor stub
	}
}

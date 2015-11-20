package boundary;

import java.awt.EventQueue;

import Controller.ProjectControl;
import Entity.Project;

public class Start {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project p = new Project();
					ProjectControl pc = new ProjectControl(p);
					new ControlPanel(p,pc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}

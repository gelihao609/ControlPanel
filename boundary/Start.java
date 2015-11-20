package boundary;

import java.awt.EventQueue;

import Controller.ProjectControl;
import Entity.Project;

public class Start {

	public static void main(String[] args) {
		Project p = new Project();
		ProjectControl pc = new ProjectControl(p);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ControlPanel(p,pc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}

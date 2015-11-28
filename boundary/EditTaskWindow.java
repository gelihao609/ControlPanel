package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Entity.Task;

public class EditTaskWindow implements Oracle {
	private JFrame frame;
	private Task taskToEdit;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTaskWindow window = new EditTaskWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public EditTaskWindow(){}

	public EditTaskWindow(Controller c) {
		initialize(c);
	}
	public EditTaskWindow(Task result) {
		taskToEdit = result;
	}
	private void initialize(Controller c) {
		frame = new JFrame("Edit a task");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(400, 200, 413, 294);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	@Override
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}

}

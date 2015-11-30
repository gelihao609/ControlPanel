package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.ResourceControl;
import Entity.Resource;
import Entity.Task;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.util.SystemOutLogger;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

public class DeleteResourceWindow implements Oracle {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteResourceWindow window = new DeleteResourceWindow(new ResourceControl());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteResourceWindow() {
	}

	public DeleteResourceWindow(Controller c) {
		initialize(c);
	}

	public DeleteResourceWindow(Resource result) {
		r = result;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize(Controller c) {
		
		
		if (r.getReferencedTasks().size() == 0) {
			((ResourceControl) c).deleteResourceFromResourcePool(r);
			JOptionPane.showMessageDialog(new JFrame("Delete Resource"), "Resource deleted successfully");

		} else {
			String tasks = r.getName()+" has following associated tasks:\n";
			for (int i = 0; i < r.getReferencedTasks().size(); i++) {
				tasks = tasks + (r.getReferencedTasks().get(i)).toString() + "\n";
			}
			tasks = tasks+"\nDo you still want to delete this resource?";
			String[] buttons = {"Yes","No"};
			int choice = JOptionPane.showOptionDialog(null, tasks, "Confirm Deletion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[1]);
			if(choice == 0){
				for (int i = 0; i < r.getReferencedTasks().size(); i++) {
					Task task = r.getReferencedTasks().get(i);
					task.unassignResource(r);
				}
				((ResourceControl) c).deleteResourceFromResourcePool(r);
				JOptionPane.showMessageDialog(new JFrame("Delete Resource"), "Resource deleted successfully");
			}
			
		}

	}

	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}

	private Resource r;
	private JTextField textFieldResourceName;
	private JTextField textFieldType;
	private JTextField textFieldCost;
}

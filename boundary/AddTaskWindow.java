package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.TaskControl;
import Entity.Task;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTaskWindow implements Oracle {
	
	private JFrame frame;
	private JTextField taskNameTF;
	private JTextField durationTF;
	private JTextArea DescTA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTaskWindow window = new AddTaskWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AddTaskWindow(){}
	/**
	 * Create the application.
	 * @param c 
	 */
	public AddTaskWindow(Controller c) {
		initialize(c);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param c 
	 * @wbp.parser.entryPoint
	 */
	public void initialize(Controller c) {
		frame = new JFrame("Add a task");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 413, 294);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Task name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 11, 74, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPredecessor = new JLabel("Add predecessor:");
		lblPredecessor.setHorizontalAlignment(SwingConstants.LEFT);
		lblPredecessor.setBounds(227, 11, 118, 16);
		frame.getContentPane().add(lblPredecessor);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setBounds(12, 42, 74, 16);
		frame.getContentPane().add(lblDuration);
		
		taskNameTF = new JTextField();
		taskNameTF.setBounds(99, 13, 116, 22);
		frame.getContentPane().add(taskNameTF);
		taskNameTF.setColumns(10);
		
		durationTF = new JTextField();
		durationTF.setBounds(99, 39, 116, 22);
		frame.getContentPane().add(durationTF);
		durationTF.setColumns(10);
		
		JLabel lblAssignResource = new JLabel("Assign Resource:");
		lblAssignResource.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAssignResource.setBounds(227, 103, 99, 16);
		frame.getContentPane().add(lblAssignResource);
		
		JButton btnAdd = new JButton("Add");

		btnAdd.setBounds(118, 212, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");

		btnCancel.setBounds(227, 212, 97, 25);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(12, 67, 74, 16);
		frame.getContentPane().add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 67, 116, 129);
		frame.getContentPane().add(scrollPane);
		DescTA = new JTextArea();
		scrollPane.setViewportView(DescTA);
		
		JScrollPane predSPane = new JScrollPane();
		predSPane.setBounds(227, 29, 141, 75);
		frame.getContentPane().add(predSPane);
		
		JList<String> predlist = new JList<String>();
		predSPane.setViewportView(predlist);
		
		JScrollPane resourceSPn = new JScrollPane();
		resourceSPn.setBounds(227, 120, 141, 75);
		frame.getContentPane().add(resourceSPn);
		
		JList<String> resourcelist = new JList<String>();
		resourceSPn.setViewportView(resourcelist);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task temT = collect();
				((TaskControl) c).addTaskToTaskPool(temT);
			}
			private Task collect() {
				String name = taskNameTF.getText();
				String duration = durationTF.getText();
				String description = DescTA.getText();
				// TODO: finish IList showing tasks and resources
			return new Task(name,duration,description,((TaskControl) c).getProject());
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});	
		}



	@Override
	public Object ask(String cmd, Controller control) {
			initialize(control);
		return null;
	}
}

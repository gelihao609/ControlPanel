package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.TaskControl;
import Entity.Resource;
import Entity.ResourcePool;
import Entity.Task;
import Entity.TaskPool;
import boundary.Oracle;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewTaskWindow implements Oracle{
	private JFrame frame;
	private JTextField taskNameTF;
	private JTextField durationTF;
	private JTextField startTF;
	private JTextField endTF;
	private JTextField parentTF;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTaskWindow window = new ViewTaskWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewTaskWindow() {
	}

	/**
	 * Create the application.
	 * 
	 * @param c
	 */
	public ViewTaskWindow(Controller c) {
		initialize(c);
	}
	public ViewTaskWindow(Task result) {
		t=result;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param c
	 * @wbp.parser.entryPoint
	 */
	public void initialize(Controller c) {
		frame = new JFrame("Task Details");
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setBounds(400, 200, 552, 290);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Task name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 11, 74, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPredecessor = new JLabel("Predecessors:");
		lblPredecessor.setHorizontalAlignment(SwingConstants.LEFT);
		lblPredecessor.setBounds(227, 11, 118, 16);
		frame.getContentPane().add(lblPredecessor);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setBounds(12, 42, 74, 16);
		frame.getContentPane().add(lblDuration);

		taskNameTF = new JTextField();
		taskNameTF.setEditable(false);
		taskNameTF.setBounds(99, 13, 116, 22);
		frame.getContentPane().add(taskNameTF);
		taskNameTF.setColumns(10);

		durationTF = new JTextField();
		durationTF.setEditable(false);
		durationTF.setBounds(99, 39, 116, 22);
		frame.getContentPane().add(durationTF);
		durationTF.setColumns(10);

		JLabel lblAssignResource = new JLabel("Assigned Resources:");
		lblAssignResource.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignResource.setBounds(227, 134, 130, 16);
		frame.getContentPane().add(lblAssignResource);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(12, 67, 74, 16);
		frame.getContentPane().add(lblDescription);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 67, 116, 83);
		frame.getContentPane().add(scrollPane);
		
		JTextArea descTA = new JTextArea();
		descTA.setEditable(false);
		scrollPane.setViewportView(descTA);

		JScrollPane predSPane = new JScrollPane();
		predSPane.setBounds(227, 29, 141, 42);
		frame.getContentPane().add(predSPane);

		JTextArea predTA = new JTextArea();
		predTA.setEditable(false);
		predSPane.setViewportView(predTA);

		JScrollPane resourceSPn = new JScrollPane();
		resourceSPn.setBounds(227, 160, 141, 42);
		frame.getContentPane().add(resourceSPn);
		
				JTextArea assnResTA = new JTextArea();
				assnResTA.setEditable(false);
				resourceSPn.setViewportView(assnResTA);

		JLabel lblNewLabel_1 = new JLabel("Successors:");
		lblNewLabel_1.setBounds(378, 12, 74, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(381, 29, 138, 42);
		frame.getContentPane().add(scrollPane_1);
		
				JTextArea succTA = new JTextArea();
				succTA.setEditable(false);
				scrollPane_1.setViewportView(succTA);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(24, 160, 74, 14);
		frame.getContentPane().add(lblStartDate);
		
		startTF = new JTextField();
		startTF.setEditable(false);
		startTF.setBounds(99, 159, 116, 20);
		frame.getContentPane().add(startTF);
		startTF.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(24, 188, 74, 14);
		frame.getContentPane().add(lblEndDate);
		
		endTF = new JTextField();
		endTF.setEditable(false);
		endTF.setBounds(99, 185, 116, 20);
		frame.getContentPane().add(endTF);
		endTF.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Parent:");
		lblNewLabel_2.setBounds(227, 82, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		parentTF = new JTextField();
		parentTF.setEditable(false);
		parentTF.setBounds(227, 103, 102, 20);
		frame.getContentPane().add(parentTF);
		parentTF.setColumns(10);
		
		JLabel lblChildren = new JLabel("Children:");
		lblChildren.setBounds(378, 82, 54, 14);
		frame.getContentPane().add(lblChildren);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(378, 103, 141, 47);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea childrenTA = new JTextArea();
		childrenTA.setEditable(false);
		scrollPane_2.setViewportView(childrenTA);
		
		JButton OKButton = new JButton("OK");
		OKButton.setBounds(225, 217, 89, 23);
		frame.getContentPane().add(OKButton);
		
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		//Setting fields in View Task Window
		taskNameTF.setText(t.getName());
		durationTF.setText(t.getDuration());
		descTA.setText(t.getDescr());
		if(t.getStartDate()!=null)
			startTF.setText((t.getStartDate()).toString());
		else
			startTF.setText("N/A");
		if(t.getEndDate()!=null)
				endTF.setText((t.getEndDate()).toString());
		else
				endTF.setText("N/A");
		String tasks="";
		for(int i=0;i<t.getPredecessor().size();i++){
			tasks=tasks+(t.getPredecessor().get(i)).toString()+"\n";
		}
		if(tasks!="")
			predTA.setText(tasks);
		else
			predTA.setText("N/A");
		tasks="";
		for(int i=0;i<t.getSuccessor().size();i++){
			tasks=tasks+(t.getSuccessor().get(i)).toString()+"\n";
		}
		if(tasks!="")
			succTA.setText(tasks);
		else
			succTA.setText("N/A");
		
		if(t.getParent().getName()!="")
			parentTF.setText(t.getParent().getName());
		else
			parentTF.setText("N/A");
		
		String children="";
		for(int i=0;i<t.getChildren().size();i++){
			children=children+(t.getChildren().get(i)).toString()+"\n";
		}
		if(children!="")
			childrenTA.setText(children);
		else
			childrenTA.setText("N/A");
		
		String resources="";
		for(int i=0;i<t.getResource().size();i++){
			resources=resources+(t.getResource().get(i)).toString()+"\n";
		}
		if(resources!="")
			assnResTA.setText(resources);
		else
			assnResTA.setText("N/A");
	}
	public Object ask(String cmd, Controller control) {
		initialize(control);
	return null;
}
	private Task t;
}

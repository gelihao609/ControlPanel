package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.TaskControl;
import Entity.ListResource;
import Entity.ListTask;
import Entity.Resource;
import Entity.Task;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		frame.setBounds(400, 200, 587, 285);
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
		durationTF.setToolTipText("If you add children, duration will be calculated in terms of children.");
		durationTF.setBounds(99, 39, 116, 22);
		frame.getContentPane().add(durationTF);
		durationTF.setColumns(10);
		
		JLabel lblAssignResource = new JLabel("Assign Resource:");
		lblAssignResource.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignResource.setBounds(344, 11, 118, 16);
		frame.getContentPane().add(lblAssignResource);
		
		JButton btnAdd = new JButton("Add");

		btnAdd.setBounds(230, 200, 97, 36);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");

		btnCancel.setBounds(347, 200, 97, 36);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(12, 67, 74, 16);
		frame.getContentPane().add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 67, 116, 120);
		frame.getContentPane().add(scrollPane);
		DescTA = new JTextArea();
		scrollPane.setViewportView(DescTA);
		
		JScrollPane predSPane = new JScrollPane();
		predSPane.setBounds(227, 35, 100, 126);
		frame.getContentPane().add(predSPane);
		JList<Task> predlist = new JList<Task>();
		predSPane.setViewportView(predlist);
		ListModel<Task> predListModel = new ListTask(((TaskControl) c).getProject().getTaskPool());
		predlist.setModel(predListModel);
		
		JScrollPane resourceSPn = new JScrollPane();
		resourceSPn.setBounds(344, 35, 100, 126);
		frame.getContentPane().add(resourceSPn);
		JList<Resource> resourcelist = new JList<Resource>();
		resourceSPn.setViewportView(resourcelist);
		ListModel<Resource> rscListModel = new ListResource(((TaskControl) c).getProject().getResourcePool());
		resourcelist.setModel(rscListModel);
		
		JLabel lblAddChildren = new JLabel("Add children:");
		lblAddChildren.setBounds(461, 11, 108, 16);
		frame.getContentPane().add(lblAddChildren);
		
		JScrollPane childrenSPn = new JScrollPane();
		childrenSPn.setBounds(461, 35, 100, 126);
		frame.getContentPane().add(childrenSPn);
		JList<Task> childrenlist = new JList<Task>();
		childrenlist.setToolTipText("If a task is choosen as a child, all its predecessors and successors are also added as children.");
		childrenSPn.setViewportView(childrenlist);
		ListModel<Task> childListModel = new ListTask(((TaskControl) c).getProject().getTaskPool());
		childrenlist.setModel(childListModel);
		
		JButton btnPredClear = new JButton("Clear");
		btnPredClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				predlist.clearSelection();
			}
		});
		btnPredClear.setHorizontalAlignment(SwingConstants.LEFT);
		btnPredClear.setBounds(261, 164, 65, 20);
		frame.getContentPane().add(btnPredClear);
		
		JButton btnResClear = new JButton("Clear");
		btnResClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resourcelist.clearSelection();
			}
		});
		btnResClear.setBounds(377, 164, 65, 20);
		frame.getContentPane().add(btnResClear);
		
		JButton btnChildClear = new JButton("Clear");
		btnChildClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				childrenlist.clearSelection();
			}
		});
		btnChildClear.setHorizontalAlignment(SwingConstants.LEFT);
		btnChildClear.setBounds(494, 164, 65, 20);
		frame.getContentPane().add(btnChildClear);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task temT = collect();
				((TaskControl) c).addTaskToTaskPool(temT);
				frame.dispose();
			}
			private Task collect() {
				String name=taskNameTF.getText();
				if(name.equals("")) name="Untitled";
				String duration = durationTF.getText();
				if(duration.equals("")) duration="-1";
				String description="";
				if(DescTA.getText()!=null) description = DescTA.getText();
				ArrayList<Task> pred = new ArrayList<Task>(predlist.getSelectedValuesList());
				// TODO validate predecessor
				ArrayList<Resource> assignedResource = new ArrayList<Resource>(resourcelist.getSelectedValuesList());
				// TODO validate resource assign
				ArrayList<Task> child = new ArrayList<Task>(childrenlist.getSelectedValuesList());
			if(child.size()==0)
				return new Task(name,duration,description,pred,assignedResource,((TaskControl) c).getProject());
			else 
				return new Task(name,duration,description,pred,assignedResource,child,((TaskControl) c).getProject());

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

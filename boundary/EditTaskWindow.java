package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.TaskControl;
import Entity.Element;
import Entity.ListResource;
import Entity.ListTask;
import Entity.Resource;
import Entity.Task;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class EditTaskWindow implements Oracle {
	
	private JFrame frame;
	private Task taskToEdit;
	private JTextField nameTF;
	private JTextField durationTF;
	private JTextArea DescriptionTA;
	private JList<Task> tplist;
	private JList<Resource> rplist;
	private JComboBox<Task> RPcomboBox;
	private JComboBox<Task> RScomboBox;
	private JComboBox<Task> RchildcomboBox;
	private JComboBox<Element> rPcombox;
	private JComboBox<Resource> unassignReComBox;
	private ArrayList<Resource> selectedResourceToAssign;
	private Task selectedParentToChange;
	private Task selectedParentToAdd;
	private ArrayList<Task> selectedPredToAdd;
	private ArrayList<Task> selectedSuccToAdd;
	private ArrayList<Task> selectedChildToAdd;


	
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

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize(Controller c) {
		frame = new JFrame("Edit a task");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(400, 200, 503, 524);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(5, 13, 74, 16);
		frame.getContentPane().add(lblName);
		
		nameTF = new JTextField();
		nameTF.setBounds(86, 10, 116, 22);
		frame.getContentPane().add(nameTF);
		nameTF.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setBounds(5, 42, 74, 16);
		frame.getContentPane().add(lblDuration);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(5, 71, 74, 16);
		frame.getContentPane().add(lblDescription);
		
		durationTF = new JTextField();
		durationTF.setBounds(86, 39, 116, 22);
		frame.getContentPane().add(durationTF);
		durationTF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 71, 116, 146);
		frame.getContentPane().add(scrollPane);
		
		DescriptionTA = new JTextArea();
		scrollPane.setViewportView(DescriptionTA);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(150, 453, 97, 25);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editTask();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(259, 453, 97, 25);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 33, 124, 184);
		frame.getContentPane().add(scrollPane_1);
		
		tplist = new JList<Task>();
		scrollPane_1.setViewportView(tplist);
		ListModel<Task> predListModel = new ListTask(((TaskControl) c).getProject().getTaskPool());
		tplist.setModel(predListModel);
		
		JLabel lblTaskPool = new JLabel("Task Pool:");
		lblTaskPool.setBounds(212, 13, 74, 16);
		frame.getContentPane().add(lblTaskPool);
		
		JButton btnAddPre = new JButton("Add Predecessor");
		btnAddPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPredToAdd = new ArrayList<Task>(tplist.getSelectedValuesList());
			}
		});
		btnAddPre.setBounds(342, 33, 136, 25);
		frame.getContentPane().add(btnAddPre);
		
		JButton btnAddSuccessor = new JButton("Add Successor");
		btnAddSuccessor.setBounds(342, 67, 136, 25);
		frame.getContentPane().add(btnAddSuccessor);	
		btnAddSuccessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedSuccToAdd = new ArrayList<Task>(tplist.getSelectedValuesList());
			}
		});
		
		JButton btnAddSubtask = new JButton("Add Sub-task");
		btnAddSubtask.setBounds(342, 100, 136, 25);
		frame.getContentPane().add(btnAddSubtask);
		
		JButton btnAddParanttask = new JButton("Add Parent");
		btnAddParanttask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddParanttask.setBounds(342, 133, 136, 25);
		frame.getContentPane().add(btnAddParanttask);
		
		JButton btnChangeParanttask = new JButton("Change Parent");
		btnChangeParanttask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeParanttask.setBounds(342, 164, 136, 25);
		frame.getContentPane().add(btnChangeParanttask);
		
		JButton btnClearSelection_TP = new JButton("Clear Selection");
		btnClearSelection_TP.setBounds(342, 192, 136, 25);
		frame.getContentPane().add(btnClearSelection_TP);
		btnClearSelection_TP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						tplist.clearSelection();
			}
		});
		
		JLabel lblResourcePool = new JLabel("Resource Pool:");
		lblResourcePool.setBounds(214, 230, 116, 16);
		frame.getContentPane().add(lblResourcePool);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(214, 248, 124, 184);
		frame.getContentPane().add(scrollPane_2);
		
		rplist = new JList<Resource>();
		ListModel<Resource> rscListModel = new ListResource(((TaskControl) c).getProject().getResourcePool());
		rplist.setModel(rscListModel);
		scrollPane_2.setViewportView(rplist);
		
		JButton btnAssignResource = new JButton("Assign Resource");
		btnAssignResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedResourceToAssign = new ArrayList<Resource>(rplist.getSelectedValuesList());
			}
		});
		btnAssignResource.setBounds(342, 248, 136, 25);
		frame.getContentPane().add(btnAssignResource);
		
		JButton btnClearSelection_RP = new JButton("Clear Selection");
		btnClearSelection_RP.setBounds(342, 407, 136, 25);
		frame.getContentPane().add(btnClearSelection_RP);
		btnClearSelection_RP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						rplist.clearSelection();
			}
		});
		
		JLabel lblRemovePredecessor = new JLabel("Remove predecessor:");
		lblRemovePredecessor.setBounds(12, 230, 136, 16);
		frame.getContentPane().add(lblRemovePredecessor);
		
		RPcomboBox = new JComboBox<Task>();
		RPcomboBox.setBounds(12, 247, 190, 22);
		frame.getContentPane().add(RPcomboBox);
		
		JLabel lblRemoveSuccessor = new JLabel("Remove successor:");
		lblRemoveSuccessor.setBounds(12, 270, 136, 16);
		frame.getContentPane().add(lblRemoveSuccessor);

		RScomboBox = new JComboBox<Task>();
		RScomboBox.setBounds(12, 285, 190, 22);
		frame.getContentPane().add(RScomboBox);
		
		JLabel lblRemoveSubtask = new JLabel("Remove sub-task:");
		lblRemoveSubtask.setBounds(12, 310, 110, 16);
		frame.getContentPane().add(lblRemoveSubtask);

		RchildcomboBox = new JComboBox<Task>();
		RchildcomboBox.setBounds(12, 327, 190, 22);
		frame.getContentPane().add(RchildcomboBox);
		
		JLabel lblRemoveParenttask = new JLabel("Remove parent:");
		lblRemoveParenttask.setBounds(12, 353, 136, 16);
		frame.getContentPane().add(lblRemoveParenttask);

		rPcombox = new JComboBox<Element>();
		rPcombox.setBounds(12, 370, 190, 22);
		frame.getContentPane().add(rPcombox);
		
		JLabel lblUnassignResource = new JLabel("Unassign resource:");
		lblUnassignResource.setBounds(12, 393, 124, 16);
		frame.getContentPane().add(lblUnassignResource);

	    unassignReComBox = new JComboBox<Resource>();
		unassignReComBox.setBounds(12, 410, 190, 22);
		frame.getContentPane().add(unassignReComBox);
		
		setField();
		
	}
	protected void editTask() {
		if(selectedResourceToAssign!=null)
		{
			for(Resource r:selectedResourceToAssign){
				taskToEdit.addAssignedResource(r);
			}
		}
		if(selectedPredToAdd!=null)
		{
			for(Task t:selectedPredToAdd){
				taskToEdit.addPredecessor(t);
			}
		}
		if(selectedSuccToAdd!=null) 
		{
			for(Task t:selectedSuccToAdd){
				t.addPredecessor(taskToEdit);
			}
		}
		if(RPcomboBox.getSelectedItem()!=null)
		{
			taskToEdit.removePredecessorNsuccessor((Task)RPcomboBox.getSelectedItem());
		}
		if(RScomboBox.getSelectedItem()!=null)
		{
			((Task)RScomboBox.getSelectedItem()).removePredecessorNsuccessor(taskToEdit);
		}
		if(unassignReComBox.getSelectedItem()!=null)
		{
			taskToEdit.unassignResource((Resource)unassignReComBox.getSelectedItem());
			((Resource)unassignReComBox.getSelectedItem());
		}
	}
	private void setField() {
		nameTF.setText(taskToEdit.getName());
		durationTF.setText(taskToEdit.getDuration().equals("-1")?"N/A":taskToEdit.getDuration());
		DescriptionTA.setText(taskToEdit.getDescr());
		for(Task i:taskToEdit.getPredecessor()){ RPcomboBox.addItem(i);}
		for(Task i:taskToEdit.getSuccessor()){ RScomboBox.addItem(i);}
		for(Task i:taskToEdit.getChildren()){ RchildcomboBox.addItem(i);}
		for(int i=0;i<taskToEdit.getResource().size();i++){ unassignReComBox.addItem(taskToEdit.getResource().get(i));}
		if(taskToEdit.getParent().getClass()==Task.class){rPcombox.addItem(taskToEdit.getParent());}; 
		RPcomboBox.setSelectedIndex(-1);
		RScomboBox.setSelectedIndex(-1);
		RchildcomboBox.setSelectedIndex(-1);
		unassignReComBox.setSelectedIndex(-1);
		rPcombox.setSelectedIndex(-1);
	}
	
	@Override
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}
}

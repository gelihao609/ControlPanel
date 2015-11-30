package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.ResourceControl;
import Entity.Resource;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewResourceWindow implements Oracle {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewResourceWindow window = new ViewResourceWindow(new ResourceControl());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewResourceWindow() {
	}

	public ViewResourceWindow(Controller c) {
		initialize(c);
	}

	public ViewResourceWindow(Resource result) {
		r = result;
	}

	public void initialize(Controller c) {
		frame = new JFrame("View Resource");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(450, 300, 432, 276);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 25, 156, 164);
		frame.getContentPane().add(scrollPane);
		
		
		JLabel lblAssociatedTasks = new JLabel("Associated Tasks:");
		lblAssociatedTasks.setBounds(231, 11, 121, 14);
		frame.getContentPane().add(lblAssociatedTasks);
		
		JTextArea asscTasks = new JTextArea();
		scrollPane.setViewportView(asscTasks);
		asscTasks.setEditable(false);
		String tasks = "";
		for (int i = 0; i < r.getReferencedTasks().size(); i++) {
			tasks = tasks + (r.getReferencedTasks().get(i)).toString()+"\n";
		}
		asscTasks.setText(tasks);
		
		JLabel lblResourceName = new JLabel(" Name:");
		lblResourceName.setBounds(10, 11, 121, 14);
		frame.getContentPane().add(lblResourceName);
		
		textFieldResourceName = new JTextField();
		textFieldResourceName.setBounds(10, 25, 121, 20);
		frame.getContentPane().add(textFieldResourceName);
		textFieldResourceName.setColumns(10);
		textFieldResourceName.setText(r.getName());
		
		JLabel lblResourceType = new JLabel(" Type:");
		lblResourceType.setBounds(10, 67, 46, 14);
		frame.getContentPane().add(lblResourceType);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(10, 83, 86, 20);
		frame.getContentPane().add(textFieldType);
		textFieldType.setColumns(10);
		textFieldType.setText(r.getType());
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(10, 124, 46, 14);
		frame.getContentPane().add(lblCost);
		
		textFieldCost = new JTextField();
		textFieldCost.setBounds(10, 138, 86, 20);
		frame.getContentPane().add(textFieldCost);
		textFieldCost.setColumns(10);
		textFieldCost.setText(r.getCost());
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnOk.setBounds(145, 214, 89, 23);
		frame.getContentPane().add(btnOk);
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

package ProjectManagementTool.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JCheckBox;

import java.awt.Button;
import java.awt.Choice;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Checkbox;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class CreateProject extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main2(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProject frame = new CreateProject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateProject() {
		String[] table_data= {"name"};
		Object[] o = {"id"};
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Select Resource");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Remove Task");
		btnNewButton.setBounds(242, 99, 116, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Task");
		btnNewButton_1.setBounds(100, 133, 144, 23);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("Add task");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AddTask frame = new AddTask();
				frame.setVisible(true);				
			}
		});
		btnNewButton_2.setBounds(90, 99, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Resource"}));
		comboBox.setToolTipText("Select Resource");
		comboBox.setBounds(10, 86, 73, 20);
		JPanel p = new JPanel();
		p.add(comboBox);
		contentPane.add(p);
		
		table = new JTable();
		table.setBounds(10, 11, 414, 64);
		contentPane.add(table);
		
		
		table_1 = new JTable();
		table_1.setBounds(10, 177, 414, 74);
		contentPane.add(table_1);
		
		
		
		//JLabel lblEnterTask = new JLabel("Enter Task..");
		//lblEnterTask.setBounds(159, 8, 77, 14);
		//contentPane.add(lblEnterTask);
	}
}

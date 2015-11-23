package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entity.Project;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Controller.Controller;
import Controller.ProjectControl;

public class CreateProjectWindow implements Oracle{

	private JFrame frame;
	private JTextField nametextField;
	private JTextField authortextField_1;
	private JTextField companytextField_2;
	private Project p;
	private JDateChooser dateChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CreateProjectWindow window = new CreateProjectWindow();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CreateProjectWindow(){}
	/**
	 * Create the application.
	 * @param c 
	 */
	public CreateProjectWindow(Controller c) {
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param c 
	 */
	private void initialize(Controller c) {
		frame = new JFrame("Create...");
		frame.setBounds(450, 300, 269, 235);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(12, 13, 92, 16);
		frame.getContentPane().add(lblProjectName);
		
		JLabel lblProjectAuthor = new JLabel("Project Author:");
		lblProjectAuthor.setBounds(12, 44, 92, 16);
		frame.getContentPane().add(lblProjectAuthor);
		
		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setBounds(12, 73, 92, 16);
		frame.getContentPane().add(lblCompany);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(12, 102, 92, 16);
		frame.getContentPane().add(lblStartDate);
		
		nametextField = new JTextField();
		nametextField.setBounds(120, 10, 116, 22);
		frame.getContentPane().add(nametextField);
		nametextField.setColumns(10);
		
		authortextField_1 = new JTextField();
		authortextField_1.setBounds(120, 41, 116, 22);
		frame.getContentPane().add(authortextField_1);
		authortextField_1.setColumns(10);
		
		companytextField_2 = new JTextField();
		companytextField_2.setBounds(120, 70, 116, 22);
		frame.getContentPane().add(companytextField_2);
		companytextField_2.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(7, 147, 97, 25);
		frame.getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(139, 147, 97, 25);
		frame.getContentPane().add(btnCancel);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(120, 99, 116, 22);
		frame.getContentPane().add(dateChooser);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Project tempP=collect();
				if(tempP!=null)JOptionPane.showMessageDialog(frame, "New project is created.");
				 ((ProjectControl) c).setProjectProperties(tempP);
				frame.dispose();
			}


		});
	}
	
	
	private Project collect() {
		String name =  nametextField.getText();
		String author =  authortextField_1.getText();
		String company =  companytextField_2.getText();
		Date start =  dateChooser.getDate();
		return new Project(name,author,company,start);
	}
	
	public Project getProject() {
		return p;
	}
	public JFrame getFrame()
	{
		return frame;
	}

	@Override
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}
}

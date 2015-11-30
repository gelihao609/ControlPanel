package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.ProjectControl;
import Entity.Project;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class EditProjectWindow implements Oracle {
	private JFrame frame;
	private JTextField nameTF;
	private JTextField authorTF;
	private JTextField companyTF;
	private JDateChooser dateChooser;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//EditProjectWindow window = new EditProjectWindow();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditProjectWindow(Controller control)
	{
		initialize(control);
	}
	
	public EditProjectWindow() {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize(Controller control) {
		
		frame = new JFrame("Edit Project");
		frame.setBounds(350, 300, 237, 235);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(23, 16, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(23, 45, 56, 16);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setBounds(23, 74, 74, 16);
		frame.getContentPane().add(lblCompany);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(23, 109, 74, 16);
		frame.getContentPane().add(lblStartDate);
		
		nameTF = new JTextField();
		nameTF.setBounds(98, 13, 116, 22);
		frame.getContentPane().add(nameTF);
		nameTF.setColumns(10);
		
		authorTF = new JTextField();
		authorTF.setBounds(98, 45, 116, 22);
		frame.getContentPane().add(authorTF);
		authorTF.setColumns(10);
		
		companyTF = new JTextField();
		companyTF.setBounds(98, 74, 116, 22);
		frame.getContentPane().add(companyTF);
		companyTF.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(98, 109, 116, 22);
		frame.getContentPane().add(dateChooser);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Project tempP=collect();
				 ((ProjectControl) control).setProjectProperties(tempP);
				frame.dispose();
			}
		});
		btnSave.setBounds(12, 164, 97, 25);
		frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(117, 164, 97, 25);
		frame.getContentPane().add(btnCancel);
		
		setFields(((ProjectControl) control).getProject());
	}

	private void setFields(Project p) {
		nameTF.setText(p.getName());
		authorTF.setText(p.getAuthorName());
		companyTF.setText(p.getCompanyName());
		dateChooser.setDate(p.getStartDate());
	}
	
	private Project collect() {
		String name =  nameTF.getText();
		String author =  authorTF.getText();
		String company =  companyTF.getText();
		Date start =  dateChooser.getDate();
		return new Project(name,author,company,start);
	}

	@Override
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}
}

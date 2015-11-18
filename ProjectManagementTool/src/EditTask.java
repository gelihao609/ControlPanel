import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditTask extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTask frame = new EditTask();
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
	public EditTask() {
		setTitle("Edit Task");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Task Name");
		lblNewLabel.setBounds(35, 24, 87, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTask = new JLabel("Task ID");
		lblTask.setEnabled(false);
		lblTask.setBounds(35, 52, 46, 14);
		contentPane.add(lblTask);
		
		textField = new JTextField();
		textField.setBounds(132, 21, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(98, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnPredecessor = new JButton("Add Predecessor");
		btnPredecessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPredecessor.setBounds(34, 80, 150, 23);
		contentPane.add(btnPredecessor);
		
		JButton btnAssignResource = new JButton("Assign resource");
		btnAssignResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignResource frame = new AssignResource();
				frame.setVisible(true);
			}
		});
		btnAssignResource.setBounds(35, 114, 149, 23);
		contentPane.add(btnAssignResource);
		
		JLabel lblAssignedResourcesAre = new JLabel("Assigned Resources are:");
		lblAssignedResourcesAre.setBounds(35, 160, 138, 14);
		contentPane.add(lblAssignedResourcesAre);
		
		textField_2 = new JTextField();
		textField_2.setBounds(35, 174, 98, 77);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
}

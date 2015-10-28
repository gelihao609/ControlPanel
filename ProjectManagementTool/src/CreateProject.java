//This is a comment
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
import com.jgoodies.forms.factories.DefaultComponentFactory;


public class CreateProject extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxLabor = new JCheckBox("Labor");
		chckbxLabor.setBounds(1, 29, 97, 23);
		contentPane.add(chckbxLabor);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Equipment");
		chckbxNewCheckBox.setBounds(1, 68, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Material");
		chckbxNewCheckBox_1.setBounds(1, 94, 97, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		JButton btnNewButton = new JButton("Remove Task");
		btnNewButton.setBounds(242, 99, 116, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add resource to task");
		btnNewButton_1.setBounds(10, 124, 144, 23);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("Add task");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JTextField textField = new JTextField();
				textField.setText("This is a text");				
			}
		});
		btnNewButton_2.setBounds(147, 99, 89, 23);
		contentPane.add(btnNewButton_2);
		
		Checkbox checkbox = new Checkbox("Add task..");
		checkbox.setBounds(147, 19, 95, 22);
		contentPane.add(checkbox);
		
		Checkbox checkbox_1 = new Checkbox("Add task..");
		checkbox_1.setBounds(147, 46, 95, 22);
		contentPane.add(checkbox_1);
		
		Checkbox checkbox_2 = new Checkbox("Add task..");
		checkbox_2.setBounds(147, 72, 95, 22);
		contentPane.add(checkbox_2);
		
		Checkbox checkbox_3 = new Checkbox("Select All");
		checkbox_3.setBounds(147, 0, 95, 22);
		contentPane.add(checkbox_3);
		
		JButton btnNewButton_3 = new JButton("Add Resources");
		btnNewButton_3.setBounds(1, 0, 116, 23);
		contentPane.add(btnNewButton_3);
		
		
		
		//JLabel lblEnterTask = new JLabel("Enter Task..");
		//lblEnterTask.setBounds(159, 8, 77, 14);
		//contentPane.add(lblEnterTask);
	}
}

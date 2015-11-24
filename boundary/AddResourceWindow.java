package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.ResourceControl;
import Entity.Resource;
import Entity.Task;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.awt.event.ActionEvent;

public class AddResourceWindow implements Oracle {
	private JFrame frame;
	private JTextField nameTF;
	private JTextField costTF;
	private JComboBox<String> typeComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddResourceWindow window = new AddResourceWindow(new ResourceControl());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AddResourceWindow(){}
	public AddResourceWindow(Controller c) {
		initialize(c);
	}
	
	public void initialize(Controller c) {
		frame = new JFrame("Add Resource");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(450, 300, 242, 194);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 13, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(22, 42, 56, 16);
		frame.getContentPane().add(lblType);
		
		JLabel lblNewLabel = new JLabel("Daily Cost:");
		lblNewLabel.setBounds(22, 71, 66, 16);
		frame.getContentPane().add(lblNewLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(97, 10, 116, 22);
		frame.getContentPane().add(nameTF);
		nameTF.setColumns(10);
		
		typeComboBox = new JComboBox<String>();
		typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Labor", "Equipment", "Material"}));
		typeComboBox.setBounds(97, 39, 116, 22);
		frame.getContentPane().add(typeComboBox);
		
		costTF = new JTextField();
		costTF.setBounds(97, 68, 116, 22);
		frame.getContentPane().add(costTF);
		costTF.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resource temR = collect();
				((ResourceControl) c).addResourceToResourcePool(temR);
				frame.dispose();
			}

			private Resource collect() {
				String name = nameTF.getText();
				String rate = costTF.getText();
				String type = (String) typeComboBox.getSelectedItem();
				return new Resource(name,rate,type);
			}
		});
		btnAdd.setBounds(12, 122, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(116, 122, 97, 25);
		frame.getContentPane().add(btnCancel);
	}
	
	
	
	
	
	@Override
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}

}

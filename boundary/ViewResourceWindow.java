package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.ResourceControl;
import Entity.Resource;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	public ViewResourceWindow(){}
	public ViewResourceWindow(Controller c) {
		initialize(c);
	}
	public ViewResourceWindow(Resource result) {
		r=result;
	}
	public void initialize(Controller c) {
		frame = new JFrame("View Associated Tasks");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(450, 300, 242, 194);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 236, 166);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 236, 166);
		frame.getContentPane().add(scrollPane);
		
		textArea.setText(r.getCost()+"\n"+r.getName()+"\n"+r.getType());
	}
	public Object ask(String cmd, Controller control) {
		initialize(control);
		return null;
	}
	
	private Resource r;
}

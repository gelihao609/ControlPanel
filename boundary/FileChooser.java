package boundary;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.*;
import Entity.Project;

public class FileChooser extends JFileChooser implements Oracle {

	
	public FileChooser(String string) {
		super(string);
	}

	public FileChooser() {}

	public Object ask(String cmd, Controller control) {
		ProjectControl pc=(ProjectControl)control;
		try {		
			// TODO need to find a way to deal with opened project
			//pc.save(); 
			//JOptionPane.showMessageDialog(new JFrame("Save"), "Current project is saved.");		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FileChooser chooser = new FileChooser(System.getProperty("user.dir")+"\\PCP Output\\");
						chooser.setVisible(true);
						chooser.setDialogTitle("Open Project");
						chooser.showOpenDialog(null);
						pc.clear();
						pc.load(chooser.getSelectedFile());					
						} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

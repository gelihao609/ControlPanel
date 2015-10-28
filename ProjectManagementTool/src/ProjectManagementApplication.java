import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ProjectManagementApplication {

	private JFrame frmAManagersHand;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectManagementApplication window = new ProjectManagementApplication();
					window.frmAManagersHand.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectManagementApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAManagersHand = new JFrame();
		frmAManagersHand.setTitle("A Manager's Hand");
		frmAManagersHand.setBounds(100, 100, 450, 300);
		frmAManagersHand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmAManagersHand.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmProject = new JMenuItem("Project");
		mntmProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateProject frame = new CreateProject();
				frame.setVisible(true);
			}
		});
		mnNew.add(mntmProject);
		
		JMenuItem mntmOpenProject = new JMenuItem("Open Project..");
		mnFile.add(mntmOpenProject);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
	}

}

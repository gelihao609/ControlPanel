package boundary;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;

import ProjectManagementTool.src.ProjectManagementApplication;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ProjectControl;
import Entity.Project;

/**
 * 
 */
public class ControlPanel implements Oracle {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project p = new Project();
					ControlPanel window = new ControlPanel(p, new ProjectControl(p));
					window._mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    /**
     * Default constructor
     */
    public ControlPanel(Project p, ProjectControl pc) {
		initialize(p,pc);
    }
    
	private void initialize(Project project,ProjectControl pc) {
		//window
		_mainWindow = new JFrame();
		_mainWindow.setTitle("Control Panel");
		_mainWindow.setBounds(100, 100, 817, 600);
		_mainWindow.setVisible(true);
		_mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//menuBar
		_menuBar = new MenuBar();
		_mainWindow.setJMenuBar(_menuBar);
		_mainWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//addProjectView
		 _projectView = new ProjectView(project,new FlowLayout(FlowLayout.LEFT, 5, 5));
		_mainWindow.getContentPane().add(_projectView, BorderLayout.NORTH);

		
		JPanel left_panel = new JPanel();
		_mainWindow.getContentPane().add(left_panel, BorderLayout.WEST);
		
		//Schedule View
		ScheduleView sView = new ScheduleView();
		sView.setModel(new DefaultTableModel(
			new String[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Day", "Task"
			}
		) {
			/**
			 * 
			 */
		});
		JScrollPane scrollPane = new JScrollPane(sView);
		left_panel.add(scrollPane);
		//addMenu
		Menu projectMenu = new Menu("Project");
		Menu taskMenu = new Menu("Task");
		Menu viewMenu = new Menu("View");
		_menuBar.add(projectMenu);
		_menuBar.add(taskMenu);
		_menuBar.add(viewMenu);
		//addMenuItem
		MenuItem createProject = new MenuItem("Create");
		MenuItem openProject = new MenuItem("Open...");
		MenuItem saveProject = new MenuItem("Save");
		MenuItem closeProject = new MenuItem("Close");
		projectMenu.add(createProject);
		projectMenu.add(openProject);
		projectMenu.add(saveProject);
		projectMenu.add(closeProject);	
		//define controller, command, and oracle of a menuItem
		createProject.addController(pc, "createProject",this);
		openProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		saveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		closeProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
	}

	public Object ask(String cmd)
	{
		if(cmd.equals("createProject"))
		{
			CreateProjectWindow cpw = new CreateProjectWindow();
			if(cpw.getProject()!=null)
			return cpw.getProject();
		}
		System.out.println("Not getting user info..");
		return new Project();
	}
	
	private JFrame _mainWindow;

    /**
     * 
     */
    private MenuBar _menuBar;

    /**
     * 
     */
    private ProjectView _projectView;

    private JTable table;

}
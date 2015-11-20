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
					ControlPanel window = new ControlPanel();
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
    public ControlPanel() {
		initialize();
    }
    
	private void initialize() {
		//window
		_mainWindow = new JFrame();
		_mainWindow.setTitle("Control Panel");
		_mainWindow.setBounds(100, 100, 817, 600);
		_mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//menuBar
		_menuBar = new MenuBar();
		_mainWindow.setJMenuBar(_menuBar);
		_mainWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//addProjectView
		 _projectView = new ProjectView(new FlowLayout(FlowLayout.LEFT, 5, 5));
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
			private static final long serialVersionUID = 1L;
		});
		JScrollPane scrollPane = new JScrollPane(sView);
		left_panel.add(scrollPane);
		loadMenuBar();
		
	}

	private void loadMenuBar() {
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
				//addEventHandler
				createProject.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ask("createProject");
					}
				});
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
	
	/*private Object ask(String query) {
			if(query.equals("createProject"))
			{
				//CreateProjectWindow cpw = new CreateProjectWindow();
				//return cpw.getProject();
			}
			else return null;
	}*/
	public Object ask(String cmd)
	{
		return null;
	}
	private JFrame _mainWindow;

    /**
     * 
     */
    private MenuBar _menuBar;

    /**
     * 
     */
    private List<View> _views;
    private ProjectView _projectView;

    private JTable table;

}
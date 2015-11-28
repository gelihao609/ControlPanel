package boundary;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import Controller.Controller;
import Controller.ProjectControl;
import Controller.ResourceControl;
import Controller.ScheduleControl;
import Controller.TaskControl;
import Entity.Project;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		//define window
		_mainWindow = new JFrame();
		_mainWindow.setResizable(false);
		_mainWindow.setTitle("Control Panel");
		_mainWindow.setBounds(100, 100, 905, 600);
		_mainWindow.setVisible(true);
		_mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//define menuBar
		_menuBar = new MenuBar();
		_mainWindow.setJMenuBar(_menuBar);
		_mainWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		//addProjectView
		 _projectView = new ProjectView(project,new FlowLayout(FlowLayout.LEFT, 5, 5));
		_mainWindow.getContentPane().add(_projectView, BorderLayout.NORTH);
		//addScheduleView
		 JPanel leftPanel = new JPanel();
		 _mainWindow.getContentPane().add(leftPanel, BorderLayout.WEST);
		 leftPanel.setLayout(null);
		 _scheduleView = new ScheduleView(project.getSchedule());
		JScrollPane leftScdllPane = new JScrollPane(_scheduleView);
		leftPanel.add(leftScdllPane);
		//addTaskView
		JPanel centerPanel = new JPanel();
		_mainWindow.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		JScrollPane centerTaskSPane = new JScrollPane();
		centerPanel.add(centerTaskSPane);
		_taskView = new TaskView(project);
		centerTaskSPane.setViewportView(_taskView);
		//addResourceView
		JPanel rightPanel = new JPanel();
		_mainWindow.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(null);
		_resourceView = new ResourceView(project.getResourcePool());
		JScrollPane resourceSPane = new JScrollPane(_resourceView);
		rightPanel.add(resourceSPane);
		//set panel dimension
		leftPanel.setPreferredSize(new Dimension(300, 500));
		centerPanel.setPreferredSize(new Dimension(300,500));
		rightPanel.setPreferredSize(new Dimension(300,500));
		//set inner sPane dimension
		leftScdllPane.setBounds(5, 5, 295, 480);
		centerTaskSPane.setBounds(5, 5, 295, 480);
		resourceSPane.setBounds(5, 5, 295, 480);
		//scrollPane clear buttons
		JButton btnClearScd = new JButton("Clear");
		btnClearScd.setBounds(228, 485, 70, 20);
		leftPanel.add(btnClearScd);
		JButton btnClearTsk = new JButton("Clear");
		btnClearTsk.setBounds(228, 485, 70, 20);
		centerPanel.add(btnClearTsk);
		JButton btnClearRsc = new JButton("Clear");
		btnClearRsc.setBounds(227, 485, 70, 20);
		rightPanel.add(btnClearRsc);	
		
		//addMenu
		Menu projectMenu = new Menu("Project");
		Menu taskMenu = new Menu("Task");
		Menu resourceMenu = new Menu("Resource");
		Menu scheduleMenu = new Menu("Schedule");
		_menuBar.add(projectMenu);
		_menuBar.add(taskMenu);
		_menuBar.add(resourceMenu);
		_menuBar.add(scheduleMenu);
		MenuItem createProject = new MenuItem("Create");
		MenuItem openProject = new MenuItem("Open...");
		MenuItem saveProject = new MenuItem("Save");
		MenuItem closeProject = new MenuItem("Close");
		projectMenu.add(createProject);
		projectMenu.add(openProject);
		projectMenu.add(saveProject);
		projectMenu.add(closeProject);
		//addMenuItem in Task
		MenuItem addTask = new MenuItem("Add task");
		MenuItem editTask = new MenuItem("Edit task");
		MenuItem deleteTask = new MenuItem("Delete task");
		MenuItem viewTask = new MenuItem("View task");
		taskMenu.add(addTask);
		taskMenu.add(editTask);
		taskMenu.add(viewTask);
		taskMenu.add(deleteTask);
		
		//addMenuItem in Resource
		MenuItem addResource = new MenuItem("Add resource");
		MenuItem editResource = new MenuItem("Edit resource");
		MenuItem viewResource = new MenuItem("View Resource");
		MenuItem delResource = new MenuItem("Delete resource");
		resourceMenu.add(addResource);
		resourceMenu.add(editResource);
		resourceMenu.add(viewResource);
		resourceMenu.add(delResource);
		//define controller, command, and oracle of a menuItem
		//Project
		createProject.addController(pc, "createProject",new CreateProjectWindow());
		saveProject.addController(pc,"saveProject",null);// TODO add save as fileChooser
		openProject.addController(pc,"openProject",new FileChooser());
		closeProject.addController(pc,"closeProject",null);
		//Schedule
		MenuItem generScdl = new MenuItem("Generate Schedule");
		scheduleMenu.add(generScdl);
		MenuItem expSchedule = new MenuItem("Export Schedule");
		scheduleMenu.add(expSchedule);
		
		ScheduleControl sc = new ScheduleControl(project);
		generScdl.addController(sc, "generateSchedule",null);
		expSchedule.addController(sc, "exportSchedule",null);
		//Task
		TaskControl tc = new TaskControl(project);
		addTask.addController(tc, "addTask", new AddTaskWindow());//addTask
		editTask.addController(tc, "editTask", _taskView);
		viewTask.addController(tc, "viewTask", _taskView);
		//Resource
		ResourceControl rc = new ResourceControl(project);
		addResource.addController(rc, "addResource", new AddResourceWindow());
		editResource.addController(rc, "editResource", _resourceView);
		viewResource.addController(rc, "viewResource",_resourceView );		
		//clear buttons actions
		btnClearScd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					}
				});
		btnClearTsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_taskView.clearSelection();
					}
				});
		btnClearRsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_resourceView.clearSelection();
					}
				});
	}

	public Object ask(String cmd,Controller c)
	{
		return null;
	}
	
	private JFrame _mainWindow;
    private MenuBar _menuBar;
    private ProjectView _projectView;
    private ScheduleView _scheduleView;
    private ResourceView _resourceView;
    private TaskView _taskView;
}
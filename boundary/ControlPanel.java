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
		_mainWindow.setBounds(100, 100, 900, 600);
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
		leftPanel.setPreferredSize(new Dimension(300,500));
		centerPanel.setPreferredSize(new Dimension(300,500));
		rightPanel.setPreferredSize(new Dimension(300,500));
		//set inner sPane dimension
		leftScdllPane.setBounds(5, 5, 295, 495);
		centerTaskSPane.setBounds(5, 5, 295, 495);
		resourceSPane.setBounds(5, 5, 295, 495);
		_mainWindow.pack();
		//addMenu
		Menu projectMenu = new Menu("Project");
		Menu taskMenu = new Menu("Task");
		Menu resourceMenu = new Menu("Resource");
		Menu viewMenu = new Menu("View");
		_menuBar.add(projectMenu);
		_menuBar.add(taskMenu);
		_menuBar.add(resourceMenu);
		_menuBar.add(viewMenu);
		//addMenuItem in Project
		MenuItem createProject = new MenuItem("Create");
		MenuItem openProject = new MenuItem("Open...");
		MenuItem saveProject = new MenuItem("Save");
		MenuItem generScdl = new MenuItem("Generate Schedule");
		MenuItem closeProject = new MenuItem("Close");
		projectMenu.add(createProject);
		projectMenu.add(openProject);
		projectMenu.add(saveProject);
		projectMenu.add(closeProject);
		projectMenu.add(generScdl);	
		//addMenuItem in Task
		MenuItem addTask = new MenuItem("Add task");
		MenuItem editTask = new MenuItem("Edit task");
		MenuItem deleteTask = new MenuItem("Delete task");
		taskMenu.add(addTask);
		taskMenu.add(editTask);
		taskMenu.add(deleteTask);
		//addMenuItem in Resource
		MenuItem addResource = new MenuItem("Add resource");
		MenuItem editResource = new MenuItem("Edit resource");
		MenuItem viewAssTasks = new MenuItem("View Associated Tasks");
		resourceMenu.add(addResource);
		resourceMenu.add(editResource);
		//addMenuItem in View
		MenuItem viewTask = new MenuItem("view task");
		MenuItem viewResource = new MenuItem("view resource");
		viewMenu.add(viewTask);
		viewMenu.add(viewResource);
		//define controller, command, and oracle of a menuItem
		//Project
		createProject.addController(pc, "createProject",new CreateProjectWindow());
		saveProject.addController(pc,"saveProject",null);// TODO add save as fileChooser
		openProject.addController(pc,"openProject",new FileChooser());
		closeProject.addController(pc,"closeProject",null);
		//Schedule
		ScheduleControl sc = new ScheduleControl(project);
		generScdl.addController(sc, "generateSchedule",null);
		//Task
		TaskControl tc = new TaskControl(project);
		addTask.addController(tc, "addTask", new AddTaskWindow());//addTask
		//Resource
		ResourceControl rc = new ResourceControl(project);
		addResource.addController(rc, "addResource", new AddResourceWindow());
		editResource.addController(rc, "editResource", new AddResourceWindow());
		viewAssTasks.addController(rc, "viewAssTasks",_resourceView );
	}

	public Object ask(String cmd,Controller c)
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
    private ProjectView _projectView;
    private ScheduleView _scheduleView;
    private ResourceView _resourceView;
    private TaskView _taskView;
}
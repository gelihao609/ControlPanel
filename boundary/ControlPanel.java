package boundary;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
//		JButton btnClearScd = new JButton("Clear");
//		btnClearScd.setBounds(228, 485, 70, 20);
//		leftPanel.add(btnClearScd);
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
		Menu helpMenu = new Menu("Help");
		_menuBar.add(projectMenu);
		_menuBar.add(taskMenu);
		_menuBar.add(resourceMenu);
		_menuBar.add(scheduleMenu);
		_menuBar.add(helpMenu);
		MenuItem createProject = new MenuItem("Create");
		MenuItem openProject = new MenuItem("Open...");
		MenuItem editProject = new MenuItem("Edit");
		MenuItem saveProject = new MenuItem("Save");
		MenuItem closeProject = new MenuItem("Close");
		projectMenu.add(createProject);
		projectMenu.add(openProject);
		projectMenu.add(editProject);
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
		editProject.addController(pc,"editProject",new EditProjectWindow());// TODO add save as fileChooser
		closeProject.addController(pc,"closeProject",null);
		//Schedule
		MenuItem generScdl = new MenuItem("Generate Schedule");
		scheduleMenu.add(generScdl);
		MenuItem expSchedule = new MenuItem("Export Schedule");
		scheduleMenu.add(expSchedule);
		
		MenuItem helpProject = new MenuItem("Documentation");
		helpMenu.add(helpProject);
		
		ScheduleControl sc = new ScheduleControl(project);
		generScdl.addController(sc, "generateSchedule",null);
		expSchedule.addController(sc, "exportSchedule",null);
		//Task
		TaskControl tc = new TaskControl(project);
		addTask.addController(tc, "addTask", new AddTaskWindow());//addTask
		editTask.addController(tc, "editTask", _taskView);
		viewTask.addController(tc, "viewTask", _taskView);
		deleteTask.addController(tc, "deleteTask", _taskView);
		//Resource
		ResourceControl rc = new ResourceControl(project);
		addResource.addController(rc, "addResource", new AddResourceWindow());
		editResource.addController(rc, "editResource", _resourceView);
		viewResource.addController(rc, "viewResource",_resourceView );	
		delResource.addController(rc, "delResource",_resourceView );
		
		helpProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("Documentation");
				JTextArea textArea = new JTextArea(5, 30);
				JScrollPane scrollPane = new JScrollPane(textArea);
				frame.setContentPane(scrollPane);
				frame.setBounds(500, 500, 500, 500);
				//scrollPane.setPreferredSize(new Dimension(450, 110));
				textArea.setText("Tip:Click on clear to unselect a selection in Task and Resource view\n"
						+ "1) Project Management Concepts:A project consists of resources and tasks\n"
						+ "2) Resources can be labor, equipment, or material.\n"
						+ "3) A resource has a name and a daily cost.\n"
						+ "4) Deliverables:A deliverable is a file (.uml, .doc, .java, .jar, .xml, etc.) or a presentation.\n"
						+ "5) Task Properties:A task has a name, description, duration, start time, end time, percent completed, "
						+ 		"a list of deliverables, and a list of required resources.\n"
						+ "6) There are two types of tasks: simple and composite.\n"
						+ "7) A composite task has sub-tasks. In particular, a composite task has a start sub-task, "
						+ 		"and a list of final sub-tasks. Of course a sub-task can be simple or composite.\n"
						+ "8) The project itself can be viewed as a single composite task called main with no predecessors of "
						+ 		"successors:\n"
						+ "9) The tool can also:\nSave and read projects to a file in ProjectML format\n"
						+ "10) Save task schedules in .xls format\n"
						+ "11) Modify the project schedule so that rows are labeled by actual dates rather than day numbers.\n"
						+ "12) Allow the project manager to specify the start date from a calendar.\n"
						+ "13) It has three JPanels to your GUI: tasks, resources, and schedule.\n"
						+ "14) In the tasks panel display all of the tasks using a JTree control.\n"
						+ "15) In the resource panel list all of the resources.\n"
						+ "16) In the schedule panel display the schedule using JTable.\n"
						+ "17) Tasks and resources should be selectable. \n"
						+ "18) So when the user selects a task or resource, then selects edit from a menu, "
						+ 	"automatically the selected task or resource is the one to best edited.");
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				frame.setVisible(true);
			}});
		
		//clear buttons actions
//		btnClearScd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//					}
//				});
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
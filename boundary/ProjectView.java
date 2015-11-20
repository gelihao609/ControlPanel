package boundary;

import java.awt.LayoutManager;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Entity.Project;

public class ProjectView extends JPanel implements View {
	
	/**
	 * 
	 */
	//entity
	private Project project;
	//views
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_5;
	
	
	/**
	 * @param layout
	 */
	public ProjectView(LayoutManager layout) {
		super(layout);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblAuthor);
		
		textField = new JTextField();
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblCompany = new JLabel("Company:");
		this.add(lblCompany);
		
		textField_2 = new JTextField();
		this.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		this.add(lblProjectName);
		
		textField_3 = new JTextField();
		this.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblProjectStartDate = new JLabel("Project Start Date:");
		this.add(lblProjectStartDate);
		
		textField_5 = new JTextField();
		this.add(textField_5);
		textField_5.setColumns(10);
	}



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}

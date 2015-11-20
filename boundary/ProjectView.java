package boundary;

import java.awt.FlowLayout;
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
    private JTextField authrorNameTF;
    private JTextField compNameTF;
    private JTextField projNameTF;
    private JTextField startDateTF;
	
	
	/**
	 * @param layout
	 */
	public ProjectView(Project p, LayoutManager layout) {
		super(layout);
		
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblAuthor);
		
		authrorNameTF = new JTextField();
		this.add(authrorNameTF);
		authrorNameTF.setColumns(10);
		authrorNameTF.setEditable(false);

		
		JLabel lblCompany = new JLabel("Company:");
		this.add(lblCompany);
		
		compNameTF = new JTextField();
		this.add(compNameTF);
		compNameTF.setColumns(10);
		compNameTF.setEditable(false);

		JLabel lblProjectName = new JLabel("Project Name:");
		this.add(lblProjectName);
		
		projNameTF = new JTextField();
		this.add(projNameTF);
		projNameTF.setColumns(10);
		projNameTF.setEditable(false);

		JLabel lblProjectStartDate = new JLabel("Project Start Date:");
		this.add(lblProjectStartDate);
		
		startDateTF = new JTextField();
		this.add(startDateTF);
		startDateTF.setColumns(10);
		startDateTF.setEditable(false);

		setProject(p);
		project.addObserver(this);
	}




	public Project getProject() {
		return project;
	}


	
	private void setProject(Project project) {
		this.project = project;
		update(null,null);
	}



	@Override
	public void update(Observable arg0, Object arg1) {
			authrorNameTF.setText(project.getName());
			compNameTF.setText(project.getCompanyName());
			projNameTF.setText(project.getProjectName());
			startDateTF.setText(project.getStartDateInString());
	}

}

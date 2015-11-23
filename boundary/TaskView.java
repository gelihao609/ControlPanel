package boundary;

import java.util.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import Entity.TaskPool;

/**
 * 
 */
public class TaskView extends JTree implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * Default constructor
     */
    public TaskView(TaskPool p) {
    	pool = p;
    	setModel(new DefaultTreeModel(
    			new DefaultMutableTreeNode("Project") {
    				/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					{
    					DefaultMutableTreeNode node_1;
    					node_1 = new DefaultMutableTreeNode("Task1");
    						node_1.add(new DefaultMutableTreeNode("Task1.1"));
    						node_1.add(new DefaultMutableTreeNode("Task1.2"));
    					add(node_1);
    					node_1 = new DefaultMutableTreeNode("Task2");
    						node_1.add(new DefaultMutableTreeNode("Task2.1"));
    						node_1.add(new DefaultMutableTreeNode("Task2.2"));
    					add(node_1);
    				}
    			}
    		));
    }


    /**
     * 
     */
    private TaskPool pool;


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
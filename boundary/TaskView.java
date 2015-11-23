package boundary;

import java.util.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Entity.Project;
import Entity.Task;
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
    public TaskView(Project p) 
    {
    	init(p);
    	pool.addObserver(this);
    }
    
	public void init(Project p)
    {
    	pool = p.getTaskPool();
    	setModel(new DefaultTreeModel(
    			new DefaultMutableTreeNode("Main") {
					/**
					 * 
					 */
					{
						traverse(this,p);
    				}
    				/**
					 *  travese to build Jtree of Task, need to test
					 */
					private void traverse(DefaultMutableTreeNode parent,Entity.Element t)
					{
						if(t.getChildren()!=null)
						for(int i=0;i<t.getChildren().size();i++)
						{
							DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(t.getChildren().get(i).getName());
							parent.add(newSon);
							traverse(newSon,t.getChildren().get(i));
						}
					}
					
					private static final long serialVersionUID = 1L;

    			}
    		));
    }
    




    /**
     * 
     */
    private TaskPool pool;


	@Override
	public void update(Observable o, Object arg) {			
		init((Project)arg);
	}




}
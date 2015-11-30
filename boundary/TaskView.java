package boundary;

import java.util.Observable;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import Controller.Controller;
import Entity.Project;
import Entity.TaskPool;

/**
 * 
 */
public class TaskView extends JTree implements TreeSelectionListener,View,Oracle {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Default constructor
     */
	

    public TaskView(Project p) 
    {
    	initialize(p);
    	this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    	//Listen for when the selection changes.
    	this.addTreeSelectionListener(this);
    }
	public void initialize(Project p)
    {
    	pool = p.getTaskPool();
    	setModel(new DefaultTreeModel(
    			new DefaultMutableTreeNode("Main") {
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
							DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(t.getChildren().get(i));
							parent.add(newSon);
							traverse(newSon,t.getChildren().get(i));
						}
					}					
					private static final long serialVersionUID = 1L;
    			}
    		));
    	pool.addObserver(this);
    }
    /**
     * 
     */
	
	
    private TaskPool pool;
	@Override
	public void update(Observable o, Object arg) {	
		initialize(((TaskPool)o).getHead());
		//System.out.println("enter TaskView update");
	}
	
	private Entity.Element seltask;
	
	public Entity.Element getSelected()
	{
		return seltask;
	}
	public void valueChanged(TreeSelectionEvent e) {
	    //Returns the last path element of the selection.
	    //This method is useful only when the selection model allows a single selection.
		
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();

	    if (node == null)
	    //Nothing is selected.  
	    return;

	    Object nodeInfo = node.getUserObject();
	    seltask= (Entity.Element) nodeInfo;
	        
	    
	    //System.out.println(nodeInfo.toString() + ":  \n    ");	    

	}
	public Object ask(String cmd,Controller control){
		return getSelected();
	}
}
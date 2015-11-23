package boundary;

import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Entity.ResourcePool;

/**
 * 
 */
public class ResourceView extends JTable implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public ResourceView(ResourcePool p) {
    	pool = p;
		 setModel(new DefaultTableModel(
			new String[][] {
				{"Group1", "10"},
				{"Group2", "15"},
				{"Group3", "20"}},new String[] {"Name", "Daily Cost"}));
		 getColumnModel().getColumn(0).setPreferredWidth(15);
		 getColumnModel().getColumn(0).setPreferredWidth(70);
    }

    /**
     * 
     */
    
    private ResourcePool pool;

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
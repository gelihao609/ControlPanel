package boundary;

import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entity.Project;
import Entity.ResourcePool;

public class ResourceView extends JTable implements View {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Default constructor
     */
    public ResourceView(ResourcePool p) {
    	initialize(p);
    }
    /**
     * 
     */
    public void initialize(ResourcePool p)
    {
    	pool=p;
    	pool.addObserver(this);
    	int totalRows = p.size();
    	String[][] table = new String[totalRows][3];
    	for(int i=0;i<totalRows;i++)
    	{
    		table[i][0]=p.get(i).getName();
    		table[i][1]=p.get(i).getType();
    		table[i][2]=p.get(i).getCost();
    	}
		 setModel(new DefaultTableModel(
				 table,new String[] {"Resource","Type","Daily Cost"}));
		// getColumnModel().getColumn(0).setPreferredWidth(15);
		// getColumnModel().getColumn(0).setPreferredWidth(70);
	    	
    }
    
    private ResourcePool pool;
	@Override
	public void update(Observable o, Object arg) {
		initialize((ResourcePool)arg);
	}
}
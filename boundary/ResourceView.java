package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Entity.Project;
import Entity.Resource;
import Entity.ResourcePool;

public class ResourceView extends JTable implements Oracle,View {
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
		 JTable table1 = this;
		ListSelectionModel cellSelectionModel = table1.getSelectionModel();
 	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	    
 	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
 	      public void valueChanged(ListSelectionEvent e) {
 	        //String selectedData = null;
 	        table1.setColumnSelectionAllowed(false);
 	       	table1.setRowSelectionAllowed(true);
 	       	int selectedRow = table1.getSelectedRow();
 	       // int selectedColumn = table1.getSelectedColumn();
 	       // Object cell = table1.getValueAt(selectedRow, selectedColumn);
 	        if(selectedRow>-1)selected = p.get(selectedRow);
 	        else System.out.println("selection fails");
 	       // System.out.println("Selected: " + p.get(selectedRow).getClass());
// 	       ViewAssociatedTasksWindow win = new ViewAssociatedTasksWindow(cell);
// 	       MenuItem viewAssTasks = new MenuItem("View Associated Tasks");
// 			viewAssTasks.setEnabled(true);
// 	       delResourcebutton.addActionListener(new ActionListener() {
//
// 	    	    @Override
// 	    	    public void actionPerformed(ActionEvent arg0) {
// 	    	        // check for selected row first
// 	    	        if (table.getSelectedRow() != -1) {
// 	    	            // remove selected row from the model
// 	    	            model.removeRow(table.getSelectedRow());
// 	    	        }
// 	    	    }
// 	    	});
 	      }

 	    });
    }
    private ResourcePool pool;
    private Resource selected;
    public Resource getSelected()
    {
    	return selected;
    }
	@Override
	public void update(Observable o, Object arg) {
		initialize((ResourcePool)arg);
	}
	@Override
	public Object ask(String cmd, Controller control) {		
		return getSelected();
	}
	
}
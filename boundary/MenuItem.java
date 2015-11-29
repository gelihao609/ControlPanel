package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import Controller.Controller;

/**
 * 
 */
public class MenuItem extends JMenuItem {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2647346354844996554L;


	/**
     * Default constructor
     */
    public MenuItem(String s) {
    	super(s);
    }
    
    public void addController(Controller c, String cmd, Oracle o)
    {
    	controller = c;
    	addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.execute(cmd,o);
			}});
    }
    

    /**
     * 
     */
    private Controller controller;

}
package Controller;

import java.util.*;

import boundary.Oracle;

/**
 * 
 */
public interface Controller {
	public void execute(String cmd, Oracle o);
}
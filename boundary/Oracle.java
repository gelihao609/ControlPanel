package boundary;

import Controller.Controller;
import Controller.ProjectControl;

public interface Oracle {
	public Object ask(String cmd, Controller control);
}

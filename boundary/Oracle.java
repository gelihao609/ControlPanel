package boundary;

import Controller.Controller;

public interface Oracle {
	public Object ask(String cmd, Controller control);
}

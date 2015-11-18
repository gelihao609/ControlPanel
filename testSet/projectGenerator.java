package testSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Entity.*;

public class projectGenerator {
	public static Project projectWithPredec()
	{
		Resource r1 = new Resource("r1");
		Resource r2 = new Resource("r2");
		Task t1 = new Task("t1");
		Task t2 = new Task("t2");
		Task t3 = new Task("t3");
		Task t4 = new Task("t4");
		List<Task> ts = new ArrayList<Task>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		ts.add(t4);
		List<Task> predec = new ArrayList<Task>();
		predec.add(t1);
		predec.add(t3);
		Task t_withPrede= new Task("havePre",null,null,predec,null,null);
		ts.add(t_withPrede);
		List<Resource> rs = new ArrayList<Resource>();
		rs.add(r2);
		rs.add(r1);
		Project p = new Project(ts,rs,"projectWithPredec");
		return p;
	}
}

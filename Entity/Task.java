package Entity;

import java.util.*;

/**
 * 
 */
public class Task extends Element {

    /**
     * Default constructor
     */
    public Task() {
    }

    /**
     * 
     */
    private Task parent;

    /**
     * 
     */
    private Set<Task> children;

    /**
     * 
     */
    private Set<Task> predecessors;

    /**
     * 
     */
    private Set<Resource> resources;

    /**
     * 
     */
    private Set<Task> successors;

}
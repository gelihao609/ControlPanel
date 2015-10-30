package boundary;

import Entity.ILoader;

/**Gateway used to load XML into objects and parse object into XML
 * 
 * 
 */
/*
 * Project XML:
 * <Project>
 * 	<ID><\ID>
 * 	<Name><\Name>
 * 	<predecessors><\predecessors>
 * 		<Task><\Task>
 * 	<successors><\successors>
 * 		<Task><\Task>
 * 	<children><\children>
 * 		<Task><\Task>
 * 	<parent><\parent>
 * 		<Task><\Task>
 * 	<TaskPool>
 * 		<Task>
 * 			<ID><\ID>
 * 			<Name><\Name>
 * 		<\Task>
 * 	<\TaskPool>
 * 	<ResourcePool>
 * 		<Resource>
 * 		<\Resource>
 * 	<\ResourcePool>
 * <\Project>
 */
public class LoaderGateway implements ILoader {

    /**
     * Default constructor
     */
    public LoaderGateway() {
    	
    }

}
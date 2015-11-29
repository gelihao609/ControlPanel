package Entity;

import java.io.File;

/**
 * 
 */
/*
 * output project into structure as follows
 * <Project>
 * 	<\id>
 * 	<\name>
 * 	<taskPool>
 * 		<task>
 * 			<\id>
 * 			<\name>
 * 			<\Date>
 * 			<\parent>
 * 			<\child>
 * 			<\predecessors>
 * 			<\successors>
 *			<\assignedResource>
 * 		<\task>
 * 		<task>
 * 		<\task>
 * 	<\taskPool>
 * <ResourcePool>
 * 	<Resource>
 * 		<\id>
 * 		<\name>
 * 		<\value>
 * 	<\Resource>
 * <\ResourcePool>
 * <\Project>
 */
public interface ILoader {
	public void save(File filename, Object model);
	public void load(File finename,Object model);

}
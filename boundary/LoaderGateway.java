package boundary;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Entity.*;

/**Gateway used to load XML into objects and parse object into XML
 * 
 * 
 */
/*
 * Project XML:
 * <Project>
 * 	<ID><\ID>
 * 	<Name><\Name>
 * <predecessors><\predecessors>
 * 	<TaskID><\TaskID>
 * <successors><\successors>
 * 	<TaskID><\TaskID>
 * <children><\children>
 * 	<TaskID><\TaskID>
 * <parent><\parent>
 * 	<TaskID><\TaskID>
 * 
 * <Task>
 * 	<ID><\ID>
 * 	<Name><\Name>
 * 	<assignedResource>
 * <\Task>
 * <Resource>
 * <\Project>
 */
public class LoaderGateway implements ILoader {

    /**
     * Default constructor
     */
    public LoaderGateway() {
    	
    }
    
    public void createXML(Project project) throws Exception{
    	DocumentBuilderFactory doucumentBuilderFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder documentBuilder = doucumentBuilderFactory.newDocumentBuilder();
    	Document document = documentBuilder.newDocument();
    	
    	Element elementProj = document.createElement("Project");
    	document.appendChild(elementProj);
    	Attr projectId = document.createAttribute("ID");
    	projectId.setValue(Integer.toString(project.getId()));
    	elementProj.setAttributeNode(projectId);
    	Attr projectName = document.createAttribute("Name");
    	projectName.setValue((project.getName()));
    	elementProj.setAttributeNode(projectName);    	
    	
    	Element elementPredec = document.createElement("TaskPool");
    	TaskPool taskPool = project.getTaskPool();
    	Iterator<Task> iterTask =taskPool.iterator();
    	while(iterTask.hasNext())
    	{
        	Task task = (Task)iterTask.next();
        	Element elementTask = document.createElement("Task");
        	Attr taskId = document.createAttribute("ID");
        	taskId.setValue(Integer.toString(task.getId()));
        	elementTask.setAttributeNode(taskId);
        	Attr taskName = document.createAttribute("Name");
        	taskName.setValue(task.getName());
        	elementTask.setAttributeNode(taskName);
    		elementPredec.appendChild(elementTask);
    	}
    	elementProj.appendChild(elementPredec);
    	
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	Transformer transformer = transformerFactory.newTransformer();
    	DOMSource source = new DOMSource(document);
    	
    	StreamResult streamResult = new StreamResult(new File(project.getName()+".xml"));
    	
    	transformer.transform(source,streamResult);
    }
    
   public Project readXML(String filename) throws SAXException, IOException, ParserConfigurationException
   {
	   File xmlfile = new File(filename+".xml");
	   DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	   Document document = documentBuilder.parse(xmlfile);
	   
	   NodeList list = document.getElementsByTagName("Project");
	   
	   List<Task> taskPoolSet = new ArrayList<Task>();
	   
	   for(int i=0;i<list.getLength();i++)
	   {
		   Node node = list.item(i);
		   if(node.getNodeType()==Node.ELEMENT_NODE)
		   {
			   Element element = (Element) node;
			   NodeList taskPoolList = element.getElementsByTagName("TaskPool");
			   Node taskPool = taskPoolList.item(0);
			   if(taskPool.getNodeType()==Node.ELEMENT_NODE)
			   {
				   Element taskPoolElement = (Element) taskPool;
				   NodeList taskSet = taskPoolElement.getElementsByTagName("Task");
				   for(int iii=0;iii<taskSet.getLength();iii++)
				   {
					   if(taskSet.item(iii).getNodeType()==Node.ELEMENT_NODE)
					   {
						   Element taskElement = (Element) taskSet.item(iii);

						   String name = taskElement.getAttribute("Name");
						   int id = Integer.parseInt(taskElement.getAttribute("ID"));
						   taskPoolSet.add(new Task(id,name));
					   }
				   }

			   }
		   }
	   }
	   return new Project(new TaskPool(taskPoolSet),null,filename);
   }

}
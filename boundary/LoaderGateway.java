package boundary;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
public class LoaderGateway implements ILoader {

    /**
     * Default constructor
     */
    public LoaderGateway() {
    	
    }
    
    private void createXML(Project project) throws Exception{
    	DocumentBuilderFactory doucumentBuilderFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder documentBuilder = doucumentBuilderFactory.newDocumentBuilder();
    	Document document = documentBuilder.newDocument();
    	
    	Element elementProj = document.createElement("Project");
    	//Project attributes
    	document.appendChild(elementProj);
    	Attr projectId = document.createAttribute("ID");
    	projectId.setValue(Integer.toString(project.getId()));
    	elementProj.setAttributeNode(projectId);
    	
    	Attr projectName = document.createAttribute("Name");
    	projectName.setValue(project.getName());
    	elementProj.setAttributeNode(projectName); 
    	
    	Attr projectDate = document.createAttribute("Date");
    	projectDate.setValue(project.getStartDateInString());
    	elementProj.setAttributeNode(projectDate);   	
    	
    	Attr projectAuth = document.createAttribute("Author");
    	projectAuth.setValue(project.getAuthorName());
    	elementProj.setAttributeNode(projectAuth); 
    	Attr projectCom = document.createAttribute("Company");
    	projectCom.setValue(project.getCompanyName());
    	elementProj.setAttributeNode(projectCom); 
    	//Tasks
    	TaskPool taskPool = project.getTaskPool();
    	for(int index=0;index<taskPool.size();index++)
    	{
        	Task task = taskPool.get(index);
        	//Element Task
        	Element elementTask = document.createElement("Task");
        		//Attribute ID
        	Attr taskId = document.createAttribute("ID");
        	taskId.setValue(Integer.toString(task.getId()));
        	elementTask.setAttributeNode(taskId);
        		//Attribute Name
        	Attr taskName = document.createAttribute("Name");
        	taskName.setValue(task.getName());
        	elementTask.setAttributeNode(taskName);
    			//Attribute Duration
        	Attr taskDuration = document.createAttribute("Duration");
        	taskDuration.setValue(task.getDuration());
        	elementTask.setAttributeNode(taskDuration);
        		//Attribute Description
        	Attr taskDecr = document.createAttribute("Description");
        	taskDecr.setValue(task.getDescr());
        	elementTask.setAttributeNode(taskDecr);
        		//sub-Element parent
        	Element elementTaskParent = document.createElement("Parent");
        			//Attribute Ref
        	Attr parentRef = document.createAttribute("Ref");
        	parentRef.setValue(Integer.toString(task.getParent().getId()));
        	elementTaskParent.setAttributeNode(parentRef);
        	elementTask.appendChild(elementTaskParent);
        		//sub-Element predecessor
        	ArrayList<Task> predecessors = task.getPredecessor();
        	for(int i=0;i<predecessors.size();i++)
        	{
            	Element elementPred = document.createElement("Predecessor");
            		//Attribute Ref
            	Attr predRef = document.createAttribute("Ref");
            	predRef.setValue(Integer.toString(predecessors.get(i).getId()));
            	elementPred.setAttributeNode(predRef);
            	elementTask.appendChild(elementPred);
        	}
        		//sub-Element assigned resources
        	ArrayList<Resource> resources = task.getResource();
        	for(int i=0;i<resources.size();i++)
        	{
            	Element elementAssignedRsc = document.createElement("AssignedRsc");
            		//Attribute Ref
            	Attr rscRef = document.createAttribute("Ref");
            	rscRef.setValue(Integer.toString(resources.get(i).getId()));
            	elementAssignedRsc.setAttributeNode(rscRef);
            	elementTask.appendChild(elementAssignedRsc);
        	}
        	elementProj.appendChild(elementTask);
    	}
    	//Resource
    	ResourcePool rp=project.getResourcePool();
    	for(int i=0;i<rp.size();i++)
    	{
        	Resource resource = rp.get(i);
    		Element elmentRsc = document.createElement("Resource");
    		//Attribute ID
        	Attr id = document.createAttribute("ID");
        	id.setValue(Integer.toString(resource.getId()));
        	elmentRsc.setAttributeNode(id);
        	//Attribute Name
        	Attr name = document.createAttribute("Name");
        	name.setValue(resource.getName());
        	elmentRsc.setAttributeNode(name);
        	//Attribute Type
        	Attr type = document.createAttribute("Type");
        	type.setValue(resource.getType());
        	elmentRsc.setAttributeNode(type);
        	//Attribute rate
        	Attr rate = document.createAttribute("Rate");
        	rate.setValue(resource.getCost());
        	elmentRsc.setAttributeNode(rate);
        	elementProj.appendChild(elmentRsc);
    	}
    	
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	Transformer transformer = transformerFactory.newTransformer();
    	DOMSource source = new DOMSource(document);
    	
    	StreamResult streamResult = new StreamResult(Utility.makeFile(project, this));
    	transformer.transform(source,streamResult);
    }
    


private void readXML(Project project, File fileName) throws SAXException, IOException, ParserConfigurationException, ParseException
   {
	   File xmlfile = fileName;
	   DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	   Document document = documentBuilder.parse(xmlfile);
	   
	   NodeList projList = document.getElementsByTagName("Project");
	   Node projNode = projList.item(0);
	   if(projNode.getNodeType()==Node.ELEMENT_NODE)
	   {
		   Element proj = (Element) projNode;
		   String name = proj.getAttribute("Name");
		   String author = proj.getAttribute("Author");
		   String company = proj.getAttribute("Company");
		   String startAsString = proj.getAttribute("Date");
		   Project tempP = new Project(name,author,company,parseStringToDate(startAsString));
		   project.setProperties(tempP);
		   //manually change id
		   String id = proj.getAttribute("ID");
		   project.setID(id);
	   }
	   else System.out.println("load project error");
	   //load all tasks
	   NodeList taskList = document.getElementsByTagName("Task");
	   HashMap<Integer,Task> taskContainer = new HashMap<Integer,Task>();
	   for(int i=0;i<taskList.getLength();i++)
	   {
		   Node taskNode = taskList.item(i);		   
		   if(taskNode.getNodeType()==Node.ELEMENT_NODE)
		   {
			   Element task = (Element) taskNode;
			   String name = task.getAttribute("Name");
			   String duration = task.getAttribute("Duration");
			   String description = task.getAttribute("Description");
			   String id = task.getAttribute("ID");
			   taskContainer.put(Integer.parseInt(id),new Task(name,duration,description,project,id));
			}
		}
	   //load all resources
	   NodeList resourceList = document.getElementsByTagName("Resource");
	   HashMap<Integer,Resource> resourceContainer = new HashMap<Integer,Resource>();
	   for(int i=0;i<resourceList.getLength();i++)
	   {
		   Node resNode = resourceList.item(i);		   
		   if(resNode.getNodeType()==Node.ELEMENT_NODE)
		   {
			   Element res = (Element) resNode;
			   String name = res.getAttribute("Name");
			   String type = res.getAttribute("Type");
			   String rate = res.getAttribute("Rate");
			   String id = res.getAttribute("ID");
			   Resource newRes = new Resource(name,type,rate,id);
			   resourceContainer.put(Integer.parseInt(id),newRes);
			   project.addResource(newRes);
			}
		}

	   //set parent,predecessor and assigned resource pointers
	   for(int i=0;i<taskList.getLength();i++)
	   {
		   setParentPredAssignedRsc(taskList.item(i),taskContainer,resourceContainer,project);
	   }	   
   }

private void setParentPredAssignedRsc(Node taskNode, HashMap<Integer, Task> taskContainer, HashMap<Integer, Resource> resourceContainer, Project project) {
		
	   if(taskNode.getNodeType()==Node.ELEMENT_NODE)
	   {
		   Element task = (Element) taskNode;
		   Task current = taskContainer.get(Integer.parseInt(task.getAttribute("ID")));
		   NodeList parentNodeList = task.getElementsByTagName("Parent");
		   Node parentNode = parentNodeList.item(0);
	   if(parentNode.getNodeType()==Node.ELEMENT_NODE)
	   {
		   Element parentElement = (Element) parentNode;
		   String parentId = parentElement.getAttribute("Ref");
		   //if parent is not project, then add, and remove child pointer from project
		   if(taskContainer.containsKey(Integer.parseInt(parentId)))
				   {
			   			current.setParent(taskContainer.get(Integer.parseInt(parentId)));
			   			project.removeChild(current);
				   }  
	   }
	   //get predecessor
	   NodeList predNodeList = task.getElementsByTagName("Predecessor");
	   for(int i1=0;i1<predNodeList.getLength();i1++)
	   {
		   Node predNode = predNodeList.item(i1);		   
		   if(predNode.getNodeType()==Node.ELEMENT_NODE)
		   {
			   Element pred = (Element) predNode;
			   String predId = pred.getAttribute("Ref");
			   if(taskContainer.containsKey(Integer.parseInt(predId)))
			   {
				   if(current==null)
				   {
					   System.out.println("Error in loader.");
				   }
				   current.addPredecessor(taskContainer.get(Integer.parseInt(predId)));
			   }
		   }
		   
	   }
	   //get assigned resource
	   NodeList resNodeList = task.getElementsByTagName("AssignedRsc");
	   for(int i2=0;i2<resNodeList.getLength();i2++)
	   {
		   Node resNode = resNodeList.item(i2);		   
		   if(resNode.getNodeType()==Node.ELEMENT_NODE)
		   {
			   Element res = (Element) resNode;
			   String resId = res.getAttribute("Ref");
			   if(resourceContainer.containsKey(Integer.parseInt(resId)))
			   {
				   current.addAssignedResource((resourceContainer.get(Integer.parseInt(resId))));
			   }
		   }
	   }
	    project.addTasks(current);
	   }
	
}



private Date parseStringToDate(String time) throws ParseException {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	return sdf.parse(time);
}



@Override
public void save(File filename, Object model) {
	try {
		createXML((Project)model);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

@Override
public void load(File fileName, Object model) {
	try {
		readXML((Project)model,fileName);
	} catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
		e.printStackTrace();
	}
	
}
}
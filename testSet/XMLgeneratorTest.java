package testSet;

import Entity.Project;
import boundary.LoaderGateway;

public class XMLgeneratorTest {
	
	public static void main(String[] args) {
		Project p = projectGenerator.projectWithPredec();
		LoaderGateway loader = new LoaderGateway();
		try {
			loader.createXML(p);
			System.out.println("XML generated");
			Project pp = loader.readXML("projectWithPredec");
		} catch (Exception e) {
			System.out.println("XML generate Error");
			e.printStackTrace();
		}
		
		
	}

}

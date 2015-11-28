package Entity;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import boundary.LoaderGateway;
import boundary.XLSLoader;

public class Utility {
    public final static long MILLISECONDS_PER_DAY = 1000L * 60 * 60 * 24;
    public static File makeFile(Project project,ILoader loader) {
 	   DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 	   String timeStamp = dateFormat.format(new Date());
 	   String extension="";
 	   if(loader.getClass()==LoaderGateway.class)extension += ".ProjectML";
 	   if(loader.getClass()==XLSLoader.class)extension += ".xls";
 		File path = new File(System.getProperty("user.dir")+"\\PCP Output\\"
 	   +timeStamp+"_"+project.getName()+extension);
 		File parentDir = path.getParentFile();
 		if(!parentDir.exists()) parentDir.mkdirs();
 		return path;
 	}
}

package boundary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Entity.ILoader;

public class XLSLoader implements ILoader {	
    public  void export(File fileName, String tabName, String[][] data) throws FileNotFoundException, IOException
    {
        //Create new workbook and tab
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(fileName);
        Sheet sheet = wb.createSheet(tabName);
 
    
        Row[] row = new Row[data.length];
        Cell[][] cell = new Cell[row.length][];
 
        //Define and Assign Cell Data from Given
        for(int i = 0; i < row.length; i ++)
        {
            row[i] = sheet.createRow(i);
            cell[i] = new Cell[data[i].length];
 
            for(int j = 0; j < cell[i].length; j ++)
            {
                cell[i][j] = row[i].createCell(j);
                cell[i][j].setCellValue(data[i][j]);
            }
 
        }

        //Export Data
        wb.write(fileOut);
        fileOut.close();
 
    }
}

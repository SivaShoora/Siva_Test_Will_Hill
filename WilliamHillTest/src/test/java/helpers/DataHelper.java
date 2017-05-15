/**
 * class DataHelper
 * Used to read data from excel to a Map using Apache POI
 * 
 * Deprecated methods are expected to return with Apache POI 4.0, so keeping implementation intact.
 * 
 * This class is not used for this PoC, since we are using Cucumber Parametrization, however, this can be extended if Excel parametrization is required as 
 * an enhancement.
 * 
 */

package helpers;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataHelper {
	
	@SuppressWarnings({ "deprecation", "resource", "null" })
	public static List<HashMap<String,String>> data()	
	{
		List<HashMap<String,String>> mydata = null;
		try
		{
			FileInputStream fs = new FileInputStream("src\\test\\resources\\testData\\TestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("SportsAndBets");
			Row HeaderRow = sheet.getRow(0);

			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String,String> currentHash = new HashMap<String,String>();
				for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
				{
					Cell currentCell = currentRow.getCell(j);

					switch (currentCell.getCellType())
					{
					case Cell.CELL_TYPE_STRING:
						System.out.print(currentCell.getStringCellValue() + "\t");
						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;
					}
				}
				mydata.add(currentHash);
			}
			fs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mydata;
	}
}

package resources.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriving {
	
	XSSFWorkbook workbook;
	
	public ExcelDataDriving() throws IOException {
	
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\copy\\Data.xlsx";
		File src= new File(path);
		FileInputStream fis = new FileInputStream(src);
		workbook= new XSSFWorkbook(fis);
	}
	
	public String getStringData(String sheetname, int row, int cell) {
	return 	workbook.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
	}
	
	public double getNumericDat(String sheetname, int row, int cell) {
		return	workbook.getSheet(sheetname).getRow(row).getCell(cell).getNumericCellValue();
	
	}

	
}

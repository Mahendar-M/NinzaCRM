package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String toreadDataFromExcelFile(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;

	}

	public int togetRowCount(String SheetName) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(SheetName).getLastRowNum();
		wb.close();
		return rowcount;

	}

}

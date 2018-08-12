package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFExcel implements Excel{

	private FileInputStream fileInputStream;
	private XSSFWorkbook xssfWorkbook;
	private XSSFSheet xssfSheet;

	@Override
	public void initializeSheet(String path, String sheetName) {
		try {
			fileInputStream = new FileInputStream(path);
			xssfWorkbook = new XSSFWorkbook(fileInputStream);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception during initialization!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception during initialization!");
			e.printStackTrace();
		}

		xssfSheet = xssfWorkbook.getSheet(sheetName);
	}

	@Override
	public boolean isSheetPresent(String sheetName) {
		if(xssfSheet!=null)
			return false;
		else
			return true;
	}

	@Override
	public String getCellStringValue(int rowNum, int colNum) {
		String cellValue = null;

		Row row = xssfSheet.getRow(rowNum);
		try{
			if(row != null){
				Cell cell = row.getCell(colNum);
				try{
					if(cell != null){
						cellValue = cell.getStringCellValue();
					}
				} catch (Exception e) {
					System.out.println("Referred cell at index "+colNum+" is null!");
					e.printStackTrace();
				}			
			}
		} catch (Exception e){
			System.out.println("Referred row at index "+rowNum+" is null!");
			e.printStackTrace();
		}

		return cellValue;
	}

	@Override
	public double getCellNumericValue(int rowNum, int colNum) {
		double cellValue = 0;

		Row row = xssfSheet.getRow(rowNum);
		try{
			if(row != null){
				Cell cell = row.getCell(colNum);
				try{
					if(cell != null){
						cellValue = cell.getNumericCellValue();
					}
				} catch (Exception e) {
					System.out.println("Referred cell at index "+colNum+" is null!");
					e.printStackTrace();
				}			
			}
		} catch (Exception e){
			System.out.println("Referred row at index "+rowNum+" is null!");
			e.printStackTrace();
		}

		return cellValue;
	}

	@Override
	public int getNumOfRows(String sheetName, int colNum) {
		return xssfWorkbook.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}


}

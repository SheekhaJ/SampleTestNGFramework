package utils;

public interface Excel {
	void initializeSheet(String path, String sheetName);
	
	boolean isSheetPresent(String sheetName);
	
	String getCellStringValue(int rowNum, int colNum);
	
	double getCellNumericValue(int rowNum, int colNum);
	
	int getNumOfRows(String sheetName, int colNum);
	
}

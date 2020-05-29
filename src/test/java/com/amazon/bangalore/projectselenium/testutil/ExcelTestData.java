package com.amazon.bangalore.projectselenium.testutil;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelTestData{
	
	public static String baseUrl;
	public static String books;
	public static String searchString1;
	public static String expString1;
	public static String email;
	public static String password;
	public static String helloText;
	public static String kindle;
	public static String afterSignoutText;
	static HSSFSheet ExcelWSheet; 
	static HSSFWorkbook ExcelWBook; 
	static HSSFCell Cell;
	
	public static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testInputData1.xls";

	public static void initExcelUtils() throws Exception {
		setExcelFile(path, "Sheet1");
		baseUrl = getCellData(2, 1);
		books = getCellData(17, 1);
		searchString1 = getCellData(15, 1);
		expString1 = getCellData(16, 1);
		email = getCellData(5, 1);
		password = getCellData(4, 1);
		helloText = getCellData(9, 1);
		afterSignoutText = getCellData(14, 1);
		kindle = getCellData(18, 1);
	}
		  
	public static void setExcelFile(String path,String sheetName) throws Exception {
	    try { 
		    FileInputStream excelFile = new FileInputStream(path); 
		    ExcelWBook = new HSSFWorkbook(excelFile); 
		    ExcelWSheet = ExcelWBook.getSheet(sheetName); 
		    }
	    catch (Exception e){
		    throw (e); 
		    }
	  }
	  
	public static String getCellData(int rowNum, int colNum) throws Exception{
		  try { 
			  Cell = ExcelWSheet.getRow(rowNum).getCell(colNum); 
			  String CellData = Cell.getStringCellValue(); 
			  return CellData; 
			  }
		  catch (Exception e){
				  return""; 
				  }
	}
}

package CustomizedLibs;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public ExcelUtility(){
		
	}
	
	public ExcelUtility(String fileNameAndPath,String sheetName) throws IOException{
		File fileExcel=new File(fileNameAndPath);
		FileInputStream inputStream= new FileInputStream(fileExcel);
		wb=new XSSFWorkbook(inputStream);
		sheet=wb.getSheet(sheetName);
	}
	
	
	/**
	 * This function is used for getting the value in the excel at the row index and the column index
	 * @param row the index of row (the first row should be 0 not 1)
	 * @param column the index of column (the first column should be 0 not 1)
	 * @return the value at row index and column column which are provided. in case there is no value in the cell, then empty string value will return. 
	 */
	public String getValueAt(int row, int column){
		String value="";
		try{
			value=sheet.getRow(row).getCell(column, Row.RETURN_BLANK_AS_NULL).getStringCellValue();
			if(value.length()==0){
				value=" ";
			}
			return value;
		}
		catch(Exception ex){
			return ex.getMessage();
		}
	}
	
	
	/**
	 * This function is used for getting the total row in the sheet of file excel
	 * @return the total row of a sheet in file excel
	 */
	public int GetTotalRows(){
		try{
		return  sheet.getLastRowNum()-sheet.getFirstRowNum();	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return -1;
		}
	}
}

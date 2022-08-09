package utilitiesConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.io.Files;

public class ExcelSheetUtil {
	public static Workbook getWorkbook(String filePath) throws IOException {
		File xlFile = new File(filePath);
		FileInputStream fis = new FileInputStream(xlFile);
		return Files.getFileExtension(filePath).equalsIgnoreCase("xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis);
	}

	public static Cell getCellByIndex(Sheet sheet, int rowIdx, int colIdx){
		Row row = sheet.getRow(rowIdx);
		Cell cell = (Cell) row.getCell(colIdx);
		return cell;
	}
	
	
	public static void saveWorkbook(String filePath, Workbook workbook) throws IOException {
		workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}
	
	public static String getCellValue(Cell cell) {
		switch(cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BLANK:
			return "";
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			break;
		}
		return null;
	}
	
	public static String getCellValueByIndex(Sheet sheet, int rowIdx, int colIdx){
		Cell cell = getCellByIndex(sheet, rowIdx, colIdx);
		if (null !=cell) {
			return getCellValue(cell);
		}
		else return "";
	}
	
	public static Sheet getExcelSheet(Workbook wbs, String sheetName) throws Exception {
		sheetName = null;
		return wbs.getSheet(sheetName);
	}
	
	public static void createCellAndSetValueCell(Row row, int cellIndex, String value){
		Cell data = row.createCell(cellIndex);
		data.setCellValue(value);
	}
	
	public static void setValueToCell(Cell cell, Object value) {
		String type = value.getClass().getSimpleName();
		switch(type) {
			case "String":
				if (String.valueOf(value).equalsIgnoreCase("")) {
					cell.setCellType(CellType.BLANK);
					cell.setCellValue("");
				} else if (String.valueOf(value).contains("=")) {
					cell.setCellType(CellType.FORMULA);
					cell.setCellFormula(String.valueOf(value).substring(1));
				} else {
					cell.setCellType(CellType.STRING);
					cell.setCellValue(String.valueOf(value));
				}
				break;
			case "Boolean":
				cell.setCellType(CellType.BOOLEAN);
				cell.setCellValue((boolean) value);
				break;
			case "Integer":
				cell.setCellValue(Integer.parseInt(value.toString()));
			case "Long":
			case "Float":
			case "Double":
			case "BigDecimal":
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue((Double) value);
				break;
			case "Date":
				cell.setCellType(CellType.NUMERIC);
				Workbook workbook = cell.getSheet().getWorkbook();
				short dateFormat = workbook.createDataFormat().getFormat("MM/dd/yyyy");
				CellStyle cellStyle =  workbook.createCellStyle();
				cellStyle.setDataFormat(dateFormat);
				cell.setCellStyle(cellStyle);
				cell.setCellValue((Date) value);
				break;
			default:
				cell.setCellType(CellType.STRING);
				cell.setCellValue(value.toString());
				break;
		}
	}
	
	public static void setValueToCellByIndex(Sheet sheet, int rowIndex, int colIndex, String value, String doesWbExisited) {
		Row row = sheet.createRow(rowIndex);
		Cell cell = null;
		if(doesWbExisited=="Yes"){
			cell = row.getCell(colIndex, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		}
		else{
			cell = row.createCell(colIndex);
		}
		setValueToCell(cell, value);
	}

}

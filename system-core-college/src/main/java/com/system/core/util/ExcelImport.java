package com.system.core.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImport {
	
	Workbook msExcel = null;
	private boolean isExcel2007 = false;

	public boolean isExcel2007() {
		return this.isExcel2007;
	}

	public void setExcel2007(boolean isExcel2007) {
		this.isExcel2007 = isExcel2007;
	}

	public ExcelImport() {
		this.msExcel = new HSSFWorkbook();
	}

	public ExcelImport(boolean isExcel2007) {
		this.isExcel2007 = isExcel2007;
		if (!isExcel2007)
			this.msExcel = new HSSFWorkbook();
		else
			this.msExcel = new XSSFWorkbook();
	}

	public ExcelImport(InputStream in, boolean isExcel2007) {
		try {
			this.isExcel2007 = isExcel2007;
			if (!isExcel2007)
				this.msExcel = new HSSFWorkbook(in);
			else
				this.msExcel = new XSSFWorkbook(in);
		} catch (Exception ex) {
			System.out.println("excel读取出错!");
			ex.printStackTrace();
		}
	}

	public ExcelImport(InputStream in) {
		this(in, true);
	}

	@SuppressWarnings("deprecation")
	public List<List<Object>> readData(int sheetIndex, int beginRow) {
		Sheet sheet = this.msExcel.getSheetAt(sheetIndex);
		List<List<Object>> rowDatas = new ArrayList<List<Object>>();
		int rows = sheet.getLastRowNum() + 1;
		for (int j = beginRow; j < rows; j++) {
			Row row = sheet.getRow(j);
			if (row != null) {
				if(isRowEmpty(row)){
					continue;
				}
				List<Object> rowData = new ArrayList<Object>();
				int cells = row.getLastCellNum();
				for (int k = 0; k < cells; k++) {
					Cell cell = row.getCell(k);
					if (cell != null) {
						switch (cell.getCellType()) {
						case 0:
							if (DateUtil.isCellDateFormatted(cell)) {
								rowData.add(DateUtil.getJavaDate(cell.getNumericCellValue()));
								continue;
							}
							rowData.add(Double.valueOf(cell.getNumericCellValue()));
							break;
						case 1:
							rowData.add(cell.getRichStringCellValue().toString());
							break;
						case 2:
							rowData.add(Double.valueOf(cell.getNumericCellValue()));
							break;
						case 4:
							rowData.add(Boolean.valueOf(cell.getBooleanCellValue()));
							break;
						case 3:
							rowData.add("");
							break;
						case 5:
							rowData.add("");
							break;
						default:
							rowData.add(cell.getRichStringCellValue().toString());
							break;
						}
					} else{
						rowData.add(null);
					}
				}
				rowDatas.add(rowData);
			}
		}
		return rowDatas;
	}

	public Workbook getMsExcel() {
		return this.msExcel;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isRowEmpty(Row row) {
	   for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	       Cell cell = row.getCell(c);
	       if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK){
	           return false;
	       }
	   }
	   return true;
	}
}
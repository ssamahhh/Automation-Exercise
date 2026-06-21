package com.Ecommercee.utiles.dataReader;

import com.Ecommercee.utiles.logs.logsManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {
    public final static String TEST_DATA_PATH = "src/test/resources/test-data/";

    private ExcelReader() {
        super();
    }


    // TODO: Read Data From Excel Sheet
    public static String getExcelData(String excelFilename, String sheetName, int rowNum, int colNum) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;

        String cellData;
        try {
            workBook = new XSSFWorkbook(TEST_DATA_PATH + excelFilename);
            sheet = workBook.getSheet(sheetName);
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
            return cellData;

        } catch (IOException e) {
            logsManager.error(e.getMessage());
            return "";
        }
    }
}

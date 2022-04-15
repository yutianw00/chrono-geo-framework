package plugin.dataPlugin.excelDataPlugin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Workbook book;
        Sheet sheet = null;
        String path = "C:\\Users\\61490\\Desktop\\bird_migration.xlsx";
        try {
            InputStream inputStream = new FileInputStream(new File(path));
            book = new XSSFWorkbook(inputStream);
            sheet = book.getSheetAt(0);
            if (sheet != null) {
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();
                for (int i = firstRow; i <= lastRow; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        int firstCell = row.getFirstCellNum();
                        int lastCell = row.getLastCellNum();
                        for (int j = firstCell; j < lastCell; j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                System.out.println(cell.toString());
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

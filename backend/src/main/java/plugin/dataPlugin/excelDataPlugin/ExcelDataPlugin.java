package plugin.dataPlugin.excelDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataPlugin implements DataPlugin {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getIntro() {
        return null;
    }

    @Override
    public List<MyData> importDataFromFile(String path) {
        List<MyData> records = new ArrayList<>();
        Workbook book;
        Sheet sheet = null;
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
                        double longitude = Double.valueOf(row.getCell(6).toString());
                        double latitude = Double.valueOf(row.getCell(5).toString());
                        String[] date = row.getCell(2).toString().split("-");
                        long year = Long.valueOf(date[0]);
                        double data = Double.valueOf(row.getCell(1).toString());
                        records.add(new MyData(new Location((long)longitude, (long)latitude), year, data));
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<MyData> importDataFromAPI(String link) {
        return null;
    }

    @Override
    public boolean dataEqual(MyData d1, MyData d2) {
        return false;
    }

    @Override
    public MyData group(MyData d1, MyData d2) {
        return null;
    }
}

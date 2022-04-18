package plugin.dataPlugin.excelDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.MyData;
import org.junit.Before;
import org.junit.Test;
import plugin.dataPlugin.csvDataPlugin.CSVDataPlugin;

import java.util.List;

import static org.junit.Assert.*;

public class ExcelDataPluginTest {
    private String path;
    private DataPlugin plugin;

    @Before
    public void setUp() throws Exception {
        path = "src/main/resources/dataset/bird_migration.xlsx";
        plugin = new ExcelDataPlugin();
    }
    @Test
    public void importDataFromFile() {
        List<MyData> records = plugin.importDataFromFile(path);
        assertTrue(records.size() > 0);
    }
}
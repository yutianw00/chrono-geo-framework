package plugin.dataPlugin.csvDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.MyData;
import org.junit.Before;
import org.junit.Test;
import plugin.dataPlugin.apiDataPlugin.ApiDataPlugin;

import java.util.List;

import static org.junit.Assert.*;

public class CSVDataPluginTest {
    private String path;
    private DataPlugin plugin;

    @Before
    public void setUp() throws Exception {
        path = "src/main/resources/dataset/bird_migration.csv";
        plugin = new CSVDataPlugin();
    }

    @Test
    public void importDataFromFile() {
        List<MyData> records = plugin.importDataFromFile(path);
        assertTrue(records.size() > 0);
    }

}
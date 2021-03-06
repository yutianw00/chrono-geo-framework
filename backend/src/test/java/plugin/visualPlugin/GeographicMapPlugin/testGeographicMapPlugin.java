package plugin.visualPlugin.GeographicMapPlugin;

import framework.core.DataPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import plugin.dataPlugin.apiDataPlugin.ApiDataPlugin;
import plugin.dataPlugin.csvDataPlugin.CSVDataPlugin;
import plugin.visualPlugin.GeographicMapPlugin.GeographicMapPlugin;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class testGeographicMapPlugin extends TestCase {
    DataPlugin plugin = new CSVDataPlugin();

    List<MyData> data = new LinkedList<>();

    GeographicMapPlugin barPlotPlugin = new GeographicMapPlugin();
    MyData data1 = new MyData(new Location(1, 1), 2, 3.0);
    MyData data2 = new MyData(new Location(3, 1), 4, 6.0);
    MyData data3 = new MyData(new Location(2, 1), 6, 9.0);


    @Before
    public void setUp() throws Exception {
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data = plugin.importDataFromFile("src/main/resources/dataset/bird_migration.csv");
    }

    @Test
    public void testRender() {
        /* will inspect render result manually */
        try {
            assertTrue(barPlotPlugin.render(data, "", 1000));
        } catch (UnsupportedOperationException e) {
            System.out.println("Browser not supported, please test using another OS manually");
        }
    }
}
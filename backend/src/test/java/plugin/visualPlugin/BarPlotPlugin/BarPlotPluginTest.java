package plugin.visualPlugin.BarPlotPlugin;

import framework.core.utils.Location;
import framework.core.utils.MyData;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class BarPlotPluginTest extends TestCase {

    List<MyData> data = new LinkedList<>();
    BarPlotPlugin barPlotPlugin = new BarPlotPlugin();
    MyData data1 = new MyData(new Location(1, 1), 2, 3.0);
    MyData data2 = new MyData(new Location(1, 1), 4, 6.0);
    MyData data3 = new MyData(new Location(1, 2), 6, 9.0);


    @Before
    public void setUp() throws Exception {
        data.add(data1);
        data.add(data2);
        data.add(data3);
    }

    @Test
    public void testRender() {
        /* will inspect render result manually */
        try {
            assertTrue(barPlotPlugin.render(data, "", 0));
        } catch (UnsupportedOperationException e) {
            System.out.println("Browser not supported, please test using another OS manually");
        }
    }
}
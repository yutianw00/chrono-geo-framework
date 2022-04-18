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
//        String s = "https://storage.googleapis.com/kagglesdsdata/datasets/544367/993325/Busan_Patient_Path.json?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=gcp-kaggle-com%40kaggle-161607.iam.gserviceaccount.com%2F20220416%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20220416T182217Z&X-Goog-Expires=259199&X-Goog-SignedHeaders=host&X-Goog-Signature=26ad16b03455af1f24ecb53174c1c52f105ad607eac585623848618bd3388f292bcde7dcecc9ea4a724ad1f4c4dc0d29fc03fb7e465983064778cdc8098126d23ff0938751949ab7e7d5980b606260df07788818bb95819f44727ff5b108ec11a3744ca91c96d4e1e2b8fd73b7e3e4fd409d2d25de011f1ad6c538b1f9db1144a79a5038cf4b3dbcdf5e05d2f0e07d47e3a13fc4942dffc34e6394905dba735e2396ea67290368410f088f80e58547faeeeec6422d357e914f38b56002bf832cb031040ab9836ef07b067463c7647c01475e49b0b364e140cec80e6525b29cd7f636b609c604dd6fd1ad5edebbdc6eec3bb3d805f31ae5169e9ec74150c71eda";
//        List<MyData> data = plugin.importDataFromFile("");
        data = plugin.importDataFromFile("D:\\CMU\\17514\\hw6\\hw6-analytics-framework-remote_city\\backend\\src\\main\\resources\\dataset\\bird_migration.csv");
    }

    @Test
    public void testRender() {
        /* will inspect render result manually */
        assertTrue(barPlotPlugin.render(data, "", 1000));
    }
}
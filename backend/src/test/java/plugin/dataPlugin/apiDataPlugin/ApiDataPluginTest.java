package plugin.dataPlugin.apiDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.MyData;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApiDataPluginTest {
    private String url;
    private DataPlugin plugin;
    @Before
    public void setUp() throws Exception {
        url = "https://storage.googleapis.com/kagglesdsdata/datasets/544367/993325/Busan_Patient_Path.json?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=gcp-kaggle-com%40kaggle-161607.iam.gserviceaccount.com%2F20220416%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20220416T201921Z&X-Goog-Expires=259199&X-Goog-SignedHeaders=host&X-Goog-Signature=02dcb2b1da0547d49d63e04ec4fe98573488b4607462902dedc151c69909274df57ae032e87410198a1134e1937694ccbf93179b9f6c637a6c6292ce10a5d255a5edbc0274f78b33aad5b08a5aef765f4fcbc6b1147d0a3447b2b20e759937af5ba9ec8b3622130fb93e9d44773bd9d16e87e0a9b8935dfdae39ce17098956b2bcdade062e56d016f319f0b864fb1adecf497dca423b96699acc7114a00a23619a14a4134fa2573e4c03ab15fdfbadee44f6a86294ae55bc6ac9d7bc53d71064482a7d4c30118c8de28c2fe086da527884b5d6b43353db2d5ea00fd01fb1878180ba7c3828995794113b62acf35d5fbbdd6670bca11db145549a4cd82ee6c74d";
        plugin = new ApiDataPlugin();
    }

    @Test
    public void importDataFromAPITest() {
        List<MyData> records = plugin.importDataFromAPI(url);
        assertTrue(records.size() > 0);
    }
}
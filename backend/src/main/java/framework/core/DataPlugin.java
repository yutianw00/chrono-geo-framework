package framework.core;
import framework.core.utils.MyData;

import java.io.File;
import java.util.*;

public interface DataPlugin {

    public String getName();

    public String getIntro();

    public List<MyData> importDataFromFile(String path);

    public List<MyData> importDataFromAPI(String json);

    public boolean dataEqual(MyData d1, MyData d2);

    public MyData group(MyData d1, MyData d2);

}


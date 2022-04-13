package plugin.dataPlugin.dummyDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.MyData;

import java.io.File;
import java.util.List;

public class DummyDataPlugin implements DataPlugin {

    @Override
    public String getName() {
        return "dummy Data";
    }

    @Override
    public String getIntro() {
        return "DATA dummy plugin for testing";
    }

    @Override
    public List importDataFromFile(String path) {
        return null;
    }

    @Override
    public List importDataFromAPI(String json) {
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

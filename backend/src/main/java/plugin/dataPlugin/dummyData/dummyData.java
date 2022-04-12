package plugin.dataPlugin.dummyData;

import framework.core.DataPlugin;

import java.io.File;
import java.util.List;

public class dummyData implements DataPlugin {
    @Override
    public List importDataFromFile(File file) {
        return null;
    }

    @Override
    public List importDataFromAPI(String json) {
        return null;
    }

    @Override
    public boolean compare(Object d1, Object d2) {
        return false;
    }

    @Override
    public boolean shouldGroup(Object d1, Object d2) {
        return false;
    }
}

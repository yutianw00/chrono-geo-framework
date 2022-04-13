package plugin.dataPlugin.dummyData;

import framework.core.DataPlugin;

import java.io.File;
import java.util.List;

public class DummyData implements DataPlugin<Integer> {

    @Override
    public String getName() {
        return "dummy Data";
    }

    @Override
    public String getIntro() {
        return "DATA dummy plugin for testing";
    }

    @Override
    public List importDataFromFile(File file) {
        return null;
    }

    @Override
    public List importDataFromAPI(String json) {
        return null;
    }

    @Override
    public boolean compare(Integer d1, Integer d2) {
        return false;
    }

    @Override
    public boolean shouldGroup(Integer d1, Integer d2) {
        return false;
    }
}

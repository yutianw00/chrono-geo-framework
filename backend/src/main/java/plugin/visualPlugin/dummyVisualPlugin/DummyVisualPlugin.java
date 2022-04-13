package plugin.visualPlugin.dummyVisualPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;

import java.util.List;

public class DummyVisualPlugin implements VisualPlugin {
    @Override
    public String getName() {
        return "dummy Visual";
    }

    @Override
    public String getIntro() {
        return "VISUAL dummy plugin for testing";
    }

    @Override
    public String render(List<MyData> data) {
        return null;
    }

    @Override
    public String graphTitle() {
        return null;
    }

    @Override
    public String graphDescription() {
        return null;
    }
}

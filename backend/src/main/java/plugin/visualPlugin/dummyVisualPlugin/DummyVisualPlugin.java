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
    public boolean render(List<MyData> data) {
        System.out.println("Rendering complete!");
        return true;
    }

    @Override
    public String graphTitle() {
        return "Dummy graph Title";
    }

    @Override
    public String graphDescription() {
        return "This is a dummmy graph description";
    }
}

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
        String s = "<div>\n" +
                "  <h2>This is a dummy content made by the visual plugin</h2>\n" +
                "  <p>Assume there's a html graph here.</p>\n" +
                "</div>";
        return s;
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

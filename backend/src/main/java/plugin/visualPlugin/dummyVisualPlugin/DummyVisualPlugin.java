package plugin.visualPlugin.dummyVisualPlugin;

import framework.core.VisualPlugin;

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
    public void transform(List<Integer> input) {

    }

    @Override
    public List<Integer> tranData(List<Integer> input) {
        return null;
    }

    @Override
    public List<String> outPutData(List<Integer> intermediateState) {
        return null;
    }

    @Override
    public void helper() {

    }
}

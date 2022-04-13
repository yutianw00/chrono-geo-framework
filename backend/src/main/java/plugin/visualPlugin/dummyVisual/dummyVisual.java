package plugin.visualPlugin.dummyVisual;

import framework.core.VisualPlugin;

import java.util.List;

public class dummyVisual implements VisualPlugin {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getIntro() {
        return null;
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

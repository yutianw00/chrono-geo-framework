package framework.core;

import framework.gui.DataCell;
import framework.gui.State;
import framework.gui.VisualCell;

import java.util.ArrayList;
import java.util.List;

public class FrameworkImpl implements Framework{

    List<DataPlugin> dataPlugins;
    List<VisualPlugin> visualPlugins;
    String renderHMTL = "";

    int chosenDataPluginId = -1;
    int chosenVisualPluginId = -1;

    public FrameworkImpl() {
        dataPlugins = new ArrayList<>();
        visualPlugins = new ArrayList<>();
    }

    @Override
    public void chooseDataPlugin(int id) {
        chosenDataPluginId = id;
    }

    @Override
    public State getState() {
        List<DataCell> dataCells = new ArrayList<>();
        for (int i = 0; i < dataPlugins.size(); i++) {
            DataPlugin dp = dataPlugins.get(i);
            DataCell dc = new DataCell(dp);
            if (i == chosenDataPluginId) {
                dc.setChosen(true);
            }
            dc.setLink(i);
            dataCells.add(dc);
        }

        List<VisualCell> visualCells = new ArrayList<>();
        for (int i = 0; i < visualPlugins.size(); i++) {
            VisualPlugin vp = visualPlugins.get(i);
            VisualCell vc = new VisualCell(vp);
            if (i == chosenVisualPluginId) {
                vc.setChosen(true);
            }
            visualCells.add(vc);
        }

        return new State(dataCells, visualCells, this.renderHMTL);
    }

    @Override
    public void sortData() {

    }

    @Override
    public void groupData() {

    }

    @Override
    public void registerDataPlugin(DataPlugin dataPlugin) {
        dataPlugins.add(dataPlugin);
    }

    @Override
    public void registerVisualPlugin(VisualPlugin visualPlugin) {
        visualPlugins.add(visualPlugin);
    }

    @Override
    public void restart() {

    }

    @Override
    public void importData() {

    }

    @Override
    public void render() {
        if (chosenDataPluginId == -1 || chosenVisualPluginId == -1) {
            System.out.println("plugin not chosen yet");
        }
        // TODO: do the actual render and set renderHTML
    }
}

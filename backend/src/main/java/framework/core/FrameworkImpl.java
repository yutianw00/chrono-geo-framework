package framework.core;

import framework.core.utils.MyData;
import framework.gui.DataCell;
import framework.gui.State;
import framework.gui.VisualCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameworkImpl implements Framework{

    private List<DataPlugin> dataPlugins;
    private List<VisualPlugin> visualPlugins;
    private String renderHMTL = "";

    private int chosenDataPluginId = -1;
    private int chosenVisualPluginId = -1;

    private List<MyData> data;
    private List<MyData> groupedData;

    private String graphTitle = "";
    private String graphDescription = "";

    private String errMsg = "";

    public FrameworkImpl() {
        dataPlugins = new ArrayList<>();
        visualPlugins = new ArrayList<>();
    }

    @Override
    public void chooseDataPlugin(int id) {
        chosenDataPluginId = id;
    }

    @Override
    public void chooseVisualPlugin(int id) {
        chosenVisualPluginId = id;
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
            vc.setLink(i);
            visualCells.add(vc);
        }

        return new State(dataCells, visualCells, this.renderHMTL, this.graphTitle,
                this.graphDescription, this.errMsg);
    }

    @Override
    public void sortData() {
        Collections.sort(data, (d1, d2) -> (Long.compare(d1.getTime(), d2.getTime())));
    }

    @Override
    public void groupData() {
        groupedData = new ArrayList<>();
        MyData prevData = null;
        DataPlugin dataPlugin = dataPlugins.get(chosenDataPluginId);
        for (MyData dt : data) {
            if ((prevData != null) && dataPlugin.dataEqual(prevData, dt)) {
                prevData = dataPlugin.group(prevData, dt);
            } else {
                groupedData.add(prevData);
                prevData = dt;
            }
        }
        groupedData.add(prevData);
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
        dataPlugins = new ArrayList<>();
        visualPlugins = new ArrayList<>();
        renderHMTL = "";

        chosenDataPluginId = -1;
        chosenVisualPluginId = -1;

        data = null;
        groupedData = null;

        graphTitle = "";
        graphDescription = "";
    }

    @Override
    public void importData(String str) {
        DataPlugin dataPlugin = dataPlugins.get(chosenDataPluginId);
        data = dataPlugin.importDataFromAPI(str);
        if (data == null) {
            data = dataPlugin.importDataFromFile(str);
        }
        if (data == null) {
            errMsg = "Cannot render: cannot read from the file path or the API url. " +
                    "Please double check your input.";
        }
    }

    @Override
    public void render(String str) {
        if (chosenDataPluginId == -1 || chosenVisualPluginId == -1) {
            System.out.println("plugin not chosen yet");
            errMsg = "Cannot render: please choose a data plugin AND a visual plugin";
            return;
        }
        
        System.out.println("Rendering data from source " + str + "......");
        importData(str);

        if (data == null) { // error happened at data plugin
            return;
        }

        sortData();
        groupData();

        VisualPlugin visualPlugin = visualPlugins.get(chosenVisualPluginId);
        boolean res = visualPlugin.render(groupedData);
        if (!res) {
            errMsg = "Render err: rendering failed";
        } else {
            errMsg = "";
            System.out.println("Rendering succeed!");
        }
        graphTitle = visualPlugin.graphTitle();
        graphDescription = visualPlugin.graphDescription();

    }
}

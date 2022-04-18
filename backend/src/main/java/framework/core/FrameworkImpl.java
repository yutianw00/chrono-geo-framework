package framework.core;

import framework.core.utils.Location;
import framework.core.utils.MyData;
import framework.gui.DataCell;
import framework.gui.State;
import framework.gui.VisualCell;

import java.util.*;

public class FrameworkImpl implements Framework{

    private List<DataPlugin> dataPlugins;
    private List<VisualPlugin> visualPlugins;
    private String renderHMTL = "";

    private int chosenDataPluginId = -1;
    private int chosenVisualPluginId = -1;

    private List<MyData> data;

    private String graphTitle = "";
    private String graphDescription = "";

    private String errMsg = "";
    private boolean finishRendered = false;

    private int predictNum = 0;

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
                this.graphDescription, this.errMsg, this.finishRendered, this.predictNum);
    }

    @Override
    public void sortData() {
        Collections.sort(data, (d1, d2) -> (Long.compare(d1.getTime(), d2.getTime())));
    }

    @Override
    public void groupData() {
        return;
//        Map<String, List<MyData>> dataMap = new HashMap<>();
//        for (MyData dt : data) {
//            Location loc = dt.getLocation();
//            double longitude = loc.getLongtitude();
//            double latitude = loc.getLatitude();
//            long time = dt.getTime();
//            String hash = longitude + "," + latitude + "," + time;
//            List<MyData> lst = dataMap.getOrDefault(hash, new ArrayList<>());
//            lst.add(dt);
//            dataMap.put(hash, lst);
//        }
//
//        List<MyData> groupedData = new ArrayList<>();
//        DataPlugin dataPlugin = dataPlugins.get(chosenDataPluginId);
//
//        for (Map.Entry<String, List<MyData>> entry : dataMap.entrySet()) {
//            List<MyData> myDataList = (List<MyData>) entry.getValue();
//            MyData acc = myDataList.get(0);
//            for (int i = 1; i < myDataList.size(); i++) {
//                acc = dataPlugin.group(acc, myDataList.get(i));
//            }
//            groupedData.add(acc);
//        }
//
//        data = groupedData;
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
        renderHMTL = "";

        chosenDataPluginId = -1;
        chosenVisualPluginId = -1;

        data = null;

        graphTitle = "";
        graphDescription = "";

        errMsg = "";
        finishRendered = false;
        predictNum = 0;
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
        finishRendered = true;
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

        DataPlugin chosenDataplugin = dataPlugins.get(chosenDataPluginId);
        String dataDescription = chosenDataplugin.dataDescription();
        predictNum = chosenDataplugin.predictFuture();

        Predictor.predict(data, predictNum);
        int predictDataNum = (predictNum == -1) ? 0 : predictNum;


        VisualPlugin visualPlugin = visualPlugins.get(chosenVisualPluginId);
        boolean res = visualPlugin.render(data, dataDescription, predictDataNum);
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

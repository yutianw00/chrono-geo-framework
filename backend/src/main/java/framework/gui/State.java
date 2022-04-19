package framework.gui;

import java.util.List;

public class State {

    List<DataCell> dataCells;
    List<VisualCell> visualCells;
    String renderHMTL;

    String graphTitle;
    String graphDescription;

    String errMsg;
    String status;

    String predictionMessage = "";

    public State() {
    }

    public State(List<DataCell> dataCells, List<VisualCell> visualCells,
                 String renderHMTL, String graphTitle, String graphDescription,
                 String errMsg, boolean finishRendered, int predictNum) {
        this.dataCells = dataCells;
        this.visualCells = visualCells;
        this.renderHMTL = renderHMTL;
        this.graphTitle = graphTitle;
        this.graphDescription = graphDescription;
        this.errMsg = errMsg;

        if (finishRendered && !this.errMsg.equals("")) {
            this.status = "Rendering Failed!";
        } else if (finishRendered) {
            this.status = "Rendering succeed! Please check the other opened webpage";
        } else {
            this.status = "";
        }

        if (predictNum > 0) {
            predictionMessage = "Framework will also make prediction of future "
                    + predictNum + " data points!";
        } else if (predictNum == -1){
            predictionMessage = "Please note that this data plugin does allow any prediction. ";
        }
    }

    public void setDataCells(List<DataCell> dataCells) {
        this.dataCells = dataCells;
    }

    public void setVisualCells(List<VisualCell> visualCells) {
        this.visualCells = visualCells;
    }

    public void setRenderHMTL(String renderHMTL) {
        this.renderHMTL = renderHMTL;
    }

    public void setGraphTitle(String graphTitle) {
        this.graphTitle = graphTitle;
    }

    public void setGraphDescription(String graphDescription) {
        this.graphDescription = graphDescription;
    }

    public List<DataCell> getDataCells() {
        return dataCells;
    }

    public List<VisualCell> getVisualCells() {
        return visualCells;
    }

    public String getRenderHMTL() {
        return renderHMTL;
    }

    public String getGraphTitle() {
        return graphTitle;
    }

    public String getGraphDescription() {
        return graphDescription;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return ("{ \"datacells\": " + dataCells.toString() + "," +
                " \"visualcells\": " + visualCells.toString() + "," +
                " \"graphtitle\": \"" + graphTitle + "\"," +
                " \"graphdescription\": \" " + graphDescription + "\"," +
                " \"errmsg\": \" " + errMsg + "\"," +
                " \"status\": \" " + status + "\"," +
                " \"predictmsg\": \" " + predictionMessage + "\"," +
                " \"renderhtml\": \"" +   renderHMTL + "\"}" ).replace("null", "");
    }
}

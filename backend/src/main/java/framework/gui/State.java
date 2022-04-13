package framework.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {

    List<DataCell> dataCells;
    List<VisualCell> visualCells;
    String renderHMTL;


    public State() {
    }

    public State(List<DataCell> dataCells, List<VisualCell> visualCells, String renderHMTL) {
        this.dataCells = dataCells;
        this.visualCells = visualCells;
        this.renderHMTL = renderHMTL;
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

    @Override
    public String toString() {
        return ("{ \"datacells\": " + dataCells.toString() + "," +
                " \"visualcells\": " + visualCells.toString() + "," +
                " \"renderhtml\": \"" +   renderHMTL + "\"}" ).replace("null", "");
    }
}

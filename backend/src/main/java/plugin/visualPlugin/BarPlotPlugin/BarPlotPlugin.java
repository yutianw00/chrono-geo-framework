package plugin.visualPlugin.BarPlotPlugin;


import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import plugin.visualPlugin.ProcessInput;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;

import java.util.List;

public class BarPlotPlugin implements VisualPlugin {

    @Override
    public String getName() {
        return "Bar plot Visual";
    }

    @Override
    public String getIntro() {
        return "VISUAL Bar plot plugin";
    }

    @Override
    public boolean render(List<MyData> data) {

        Table table = new ProcessInput().processInput(data);
        // ***************** Plotting **********************

        // BAR PLOTS

        // Sum the number of fatalities from each tornado, grouping by scale
        Table fatalities1 = table.summarize("data", sum).by("time");
        // Plot
        Plot.show(
                HorizontalBarPlot.create(
                        "data by time", // plot title
                        fatalities1, // table
                        "time", // grouping column name
                        "sum [data]")); // numeric column name

        return true;
    }

    @Override
    public String graphTitle() {
        return "Bar plot graph Title";
    }

    @Override
    public String graphDescription() {
        return "This is a Bar plot graph, which display the height of different data";
    }
}
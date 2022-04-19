package plugin.visualPlugin.PieChartPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import plugin.visualPlugin.ProcessInput;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.mean;

public class PieChartVisualPlugin implements VisualPlugin {
    @Override
    public String getName() {
        return "Pie chart Visual";
    }

    @Override
    public String getIntro() {
        return "display a pie chart visualization";
    }

    @Override
    public boolean render(List<MyData> data, String dataDescription, int predictDataNum) {

        // creating a table for rendering using the data
        Table table = new ProcessInput().processInput(data);
        Table fatalities1 = table.summarize("data", mean).by("time");

        String title = dataDescription + " with respect to time";

        // Plot
        Plot.show(
                PiePlot.create( // PIE CHART
                        title, // plot title
                        fatalities1, // table
                        "time", // grouping column name
                        "mean [data]")); // numeric column name

        return true;

    }

    @Override
    public String graphTitle() {
        return "Pie chart graph Title";
    }

    @Override
    public String graphDescription() {
        return "This will display the percent of data in different places at the same time";
    }


}
package plugin.visualPlugin.dummyVisualPlugin.PieChartPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import org.icepear.echarts.Bar;
import org.icepear.echarts.render.Engine;
import plugin.visualPlugin.dummyVisualPlugin.ProcessInput;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.sum;

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
    public boolean render(List<MyData> data) {
        Table table = new ProcessInput().processInput(data);
        // ***************** Plotting **********************

        // BAR PLOTS

        // Sum the number of fatalities from each tornado, grouping by scale
        Table fatalities1 = table.summarize("data", sum).by("time");
        // Plot
        Plot.show(
                PiePlot.create(
                        "data by time", // plot title
                        fatalities1, // table
                        "time", // grouping column name
                        "sum [data]")); // numeric column name

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

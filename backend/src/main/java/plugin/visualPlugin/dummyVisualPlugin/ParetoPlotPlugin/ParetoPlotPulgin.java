package plugin.visualPlugin.dummyVisualPlugin.ParetoPlotPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import plugin.visualPlugin.dummyVisualPlugin.ProcessInput;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.sum;

public class ParetoPlotPulgin implements VisualPlugin {
    @Override
    public String getName() {
        return "Pareto plot Visual";
    }

    @Override
    public String getIntro() {
        return "VISUAL Pareto plot plugin";
    }

    @Override
    public boolean render(List<MyData> data) {
        Table table = new ProcessInput().processInput(data);
        // ***************** Plotting **********************

        // BAR PLOTS

        // Sum the number of fatalities from each tornado, grouping by scale
        Table fatalities1 = table.summarize("data", sum).by("timeColumn");

        fatalities1 = fatalities1.sortDescendingOn(fatalities1.column(1).name());
        Layout layout = Layout.builder().title("data by time").build();
        BarTrace trace = BarTrace.builder(fatalities1.categoricalColumn(0), fatalities1.numberColumn(1)).build();
        Plot.show(new Figure(layout, trace));
        // Plot
        return true;
    }

    @Override
    public String graphTitle() {
        return "Pareto plot graph Title";
    }

    @Override
    public String graphDescription() {
        return "This is a Pareto plot graph, which will display the height of different datas";
    }
}

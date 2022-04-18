package plugin.visualPlugin.ParetoPlotPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import plugin.visualPlugin.ProcessInput;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.mean;
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
    public boolean render(List<MyData> data, String dataDescription, int predictDataNum) {

        // creating a table for rendering using the data
        Table table = new ProcessInput().processInput(data);
        Table fatalities1 = table.summarize("data", mean).by("time");

        String title = dataDescription + " with respect to time";

        fatalities1 = fatalities1.sortDescendingOn(fatalities1.column(1).name());
        Layout layout = Layout.builder().title(title).build();
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

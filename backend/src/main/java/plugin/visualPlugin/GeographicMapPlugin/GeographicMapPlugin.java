package plugin.visualPlugin.GeographicMapPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import plugin.visualPlugin.ProcessInput;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.mean;

public class GeographicMapPlugin implements VisualPlugin {

    @Override
    public String getName() {
        return "Geographic map plot Visual";
    }

    @Override
    public String getIntro() {
        return "VISUAL Geographic plot plugin";
    }

    @Override
    public boolean render(List<MyData> data, String dataDescription) {
        // creating a table for rendering using the data
        Table table = new ProcessInput().processInput(data);


        table = table.where(table.nCol("latitude").isGreaterThan(0));
        NumericColumn<?> x = table.nCol("latitude");
        NumericColumn<?> y = table.nCol("longtitude");



        String title = dataDescription + " with respect to time";

        // show a legend even though there's only one trace, by setting showLegend explicitly to true
        Layout layout =
                Layout.builder()
                        .title(title)
                        .height(600)
                        .width(800)
                        .showLegend(true)
                        .build();
        Trace trace =
                ScatterTrace.builder(x, y).marker(Marker.builder().size(1).build()).name("lat/lon").build();
        Plot.show(new Figure(layout, trace, trace));

        Table fatalities1 = table.summarize("data", mean).by("time");

//        String title = dataDescription + " with respect to time";
//
//        // Plot
//        Plot.show(
//                PiePlot.create( // PIE CHART
//                        title, // plot title
//                        fatalities1, // table
//                        "time", // grouping column name
//                        "mean [data]")); // numeric column name

        return true;
    }

    @Override
    public String graphTitle() {
        return "Geographic map plot graph Title";
    }

    @Override
    public String graphDescription() {
        return "This is a geographic map plot graph, which display the trace of the data";
    }
}

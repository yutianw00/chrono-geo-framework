package plugin.visualPlugin;

import org.icepear.echarts.Scatter;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class test {
    public static void main(String[] args) throws Exception {
        Table tornadoes = Table.read().csv("D:\\CMU\\17514\\hw6_2\\tablesaw\\data\\1950-2014_torn.csv");
        tornadoes = tornadoes.where(tornadoes.nCol("Start lat").isGreaterThan(20));
        NumericColumn<?> x = tornadoes.nCol("Start lon");
        NumericColumn<?> y = tornadoes.nCol("Start lat");

        // show a legend even though there's only one trace, by setting showLegend explicitly to true
        Layout layout =
                Layout.builder()
                        .title("tornado start points")
                        .height(600)
                        .width(800)
                        .showLegend(true)
                        .build();
        Trace trace =
                ScatterTrace.builder(x, y).marker(Marker.builder().size(1).build()).name("lat/lon").build();
        Plot.show(new Figure(layout, trace));
    }
}

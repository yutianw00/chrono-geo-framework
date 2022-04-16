package plugin.visualPlugin.dummyVisualPlugin;


import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import framework.core.VisualPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;
import tech.tablesaw.api.LongColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

public class BarPlotPlugin implements VisualPlugin {

    public static void main(String[] args) throws Exception {



        // ***************** Setup *************************
        // load the data into a table
        //      Table tornadoes = Table.read().csv("D:\\CMU\\17514\\hw6\\hw6-analytics-framework-remote_city\\backend\\src\\main\\java\\plugin\\visualPlugin\\data\\tornadoes_1950-2014.csv");

//        LongColumn.create("test", )

        // Get the scale column and replace any values of -9 with the column's missing value indicator
//        IntColumn scale = tornadoes.intColumn("scale");
//        scale.set(scale.isEqualTo(-9), IntColumnType.missingValueIndicator());
//
//        // ***************** Plotting **********************
//
//        // BAR PLOTS
//
//        // Sum the number of fatalities from each tornado, grouping by scale
//        Table fatalities1 = tornadoes.summarize("fatalities", sum).by("scale");
//
//        // Plot
//        Plot.show(
//                HorizontalBarPlot.create(
//                        "fatalities by scale", // plot title
//                        fatalities1, // table
//                        "scale", // grouping column name
//                        "sum [fatalities]")); // numeric column name
//
//        // Plot the mean injuries rather than a sum.
//        Table injuries1 = tornadoes.summarize("injuries", mean).by("scale");
//
//        Plot.show(
//                HorizontalBarPlot.create(
//                        "Average number of tornado injuries by scale", injuries1, "scale", "mean [injuries]"));
//
//        // PIE PLOT
//        Plot.show(PiePlot.create("fatalities by scale", fatalities1, "scale", "sum [fatalities]"));
//
//        // PARETO PLOT
//        Table t2 = tornadoes.summarize("fatalities", sum).by("State");
//
//        t2 = t2.sortDescendingOn(t2.column(1).name());
//        Layout layout = Layout.builder().title("Tornado Fatalities by State").build();
//        BarTrace trace = BarTrace.builder(t2.categoricalColumn(0), t2.numberColumn(1)).build();
//        Plot.show(new Figure(layout, trace));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getIntro() {
        return null;
    }

    @Override
    public void render(List<MyData> data) {
        Table table = Table.create();

        long[] array = new long[data.size()];
        int i = 0;
        for (MyData myData : data) {
            long latitude = myData.getLocation().getLatitude();
            array[i++] = latitude;
        }

        LongStream longStream = Arrays.stream(array);
        LongColumn longColumn = LongColumn.create("latitude", longStream);
        table.addColumns(longColumn);


        // ***************** Plotting **********************

        // BAR PLOTS

        // Sum the number of fatalities from each tornado, grouping by scale
        Table fatalities1 = table.summarize("latitude", sum).by("latitude");
        // Plot
        Plot.show(
                HorizontalBarPlot.create(
                        "latitude by latitude", // plot title
                        fatalities1, // table
                        "latitude", // grouping column name
                        "sum [latitude]")); // numeric column name
    }

    @Override
    public String graphTitle() {
        return null;
    }

    @Override
    public String graphDescription() {
        return null;
    }
}
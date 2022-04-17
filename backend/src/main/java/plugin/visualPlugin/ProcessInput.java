package plugin.visualPlugin;

import framework.core.utils.MyData;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.LongColumn;
import tech.tablesaw.api.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class ProcessInput {

    /**
     * To build a table that plugin can use
     * @param data MyData list as input
     * @return a table that can be processed by the plugin
     */
    public Table processInput(List<MyData> data) {
        Table table = Table.create();

        double[] latitudeArray = new double[data.size()];
        double[] longtitudeArray = new double[data.size()];
        long[] timeArray = new long[data.size()];
        double[] dataArray = new double[data.size()];

        int i = 0;
        for (MyData myData : data) {
            double latitude = myData.getLocation().getLatitude();
            latitudeArray[i] = latitude;
            longtitudeArray[i] = myData.getLocation().getLongtitude();
            timeArray[i] = myData.getTime();
            dataArray[i++] = myData.getData();
        }

        DoubleStream latitudeStream = Arrays.stream(latitudeArray);
        DoubleColumn latitudeColumn = DoubleColumn.create("latitude", latitudeStream);

        DoubleStream longtitudeStream = Arrays.stream(longtitudeArray);
        DoubleColumn longtitudeColumn = DoubleColumn.create("longtitude", longtitudeStream);

        LongStream timeStream = Arrays.stream(timeArray);
        LongColumn timeColumn = LongColumn.create("time", timeStream);

        DoubleStream dataStream = Arrays.stream(dataArray);
        DoubleColumn dataColumn = DoubleColumn.create("data", dataStream);

        table.addColumns(latitudeColumn);
        table.addColumns(longtitudeColumn);
        table.addColumns(timeColumn);
        table.addColumns(dataColumn);

        return table;
    }
}

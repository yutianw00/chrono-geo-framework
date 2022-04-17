package plugin.visualPlugin.dummyVisualPlugin;

import framework.core.utils.MyData;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.LongColumn;
import tech.tablesaw.api.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class ProcessInput {
    public Table processInput(List<MyData> data) {
        Table table = Table.create();

        long[] latitudeArray = new long[data.size()];
        long[] longtitudeArray = new long[data.size()];
        long[] timeArray = new long[data.size()];
        double[] dataArray = new double[data.size()];

        int i = 0;
        for (MyData myData : data) {
            long latitude = myData.getLocation().getLatitude();
            latitudeArray[i] = latitude;
            longtitudeArray[i] = myData.getLocation().getLongtitude();
            timeArray[i] = myData.getTime();
            dataArray[i++] = myData.getData();
        }

        LongStream latitudeStream = Arrays.stream(latitudeArray);
        LongColumn latitudeColumn = LongColumn.create("latitude", latitudeStream);

        LongStream longtitudeStream = Arrays.stream(longtitudeArray);
        LongColumn longtitudeColumn = LongColumn.create("longtitude", longtitudeStream);

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

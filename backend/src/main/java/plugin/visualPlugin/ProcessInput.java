package plugin.visualPlugin;

import framework.core.utils.MyData;
import org.apache.poi.ss.formula.functions.T;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.LongColumn;
import tech.tablesaw.api.Table;

import java.util.Arrays;
import java.util.LinkedList;
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

    public List<Table> processInput1(List<MyData> data) {
        Table table = Table.create();
        Table table1 = Table.create();

        double[] latitudeArray = new double[data.size() / 2 + 1];
        double[] longtitudeArray = new double[data.size() / 2 + 1];
        long[] timeArray = new long[data.size()/ 2 + 1];
        double[] dataArray = new double[data.size() / 2 + 1];

        double[] latitudeArray1 = new double[data.size() / 2 + 1];
        double[] longtitudeArray1 = new double[data.size() / 2 + 1];
        long[] timeArray1 = new long[data.size() / 2 + 1];
        double[] dataArray1 = new double[data.size() / 2 + 1];

        int i;
        for (i = 0; i < data.size() / 2; i++) {
            double latitude = data.get(i).getLocation().getLatitude();
            latitudeArray[i] = latitude;
            longtitudeArray[i] = data.get(i).getLocation().getLongtitude();
            timeArray[i] = data.get(i).getTime();
            dataArray[i++] = data.get(i).getData();
        }

        int j = 0;
        for (i = data.size() / 2; i < data.size(); i++) {
            double latitude = data.get(i).getLocation().getLatitude();
            latitudeArray1[j] = latitude;
            longtitudeArray1[j] = data.get(i).getLocation().getLongtitude();
            timeArray1[j] = data.get(i).getTime();
            dataArray1[j++] = data.get(i).getData();
        }

        DoubleStream latitudeStream = Arrays.stream(latitudeArray);
        DoubleColumn latitudeColumn = DoubleColumn.create("latitude", latitudeStream);

        DoubleStream longtitudeStream = Arrays.stream(longtitudeArray);
        DoubleColumn longtitudeColumn = DoubleColumn.create("longtitude", longtitudeStream);

        LongStream timeStream = Arrays.stream(timeArray);
        LongColumn timeColumn = LongColumn.create("time", timeStream);

        DoubleStream dataStream = Arrays.stream(dataArray);
        DoubleColumn dataColumn = DoubleColumn.create("data", dataStream);

        DoubleStream latitudeStream1 = Arrays.stream(latitudeArray1);
        DoubleColumn latitudeColumn1 = DoubleColumn.create("latitude", latitudeStream1);

        DoubleStream longtitudeStream1 = Arrays.stream(longtitudeArray1);
        DoubleColumn longtitudeColumn1 = DoubleColumn.create("longtitude", longtitudeStream1);

        LongStream timeStream1 = Arrays.stream(timeArray1);
        LongColumn timeColumn1 = LongColumn.create("time", timeStream1);

        DoubleStream dataStream1 = Arrays.stream(dataArray1);
        DoubleColumn dataColumn1 = DoubleColumn.create("data", dataStream1);


        table.addColumns(latitudeColumn);
        table.addColumns(longtitudeColumn);
        table.addColumns(timeColumn);
        table.addColumns(dataColumn);

        table1.addColumns(latitudeColumn1);
        table1.addColumns(longtitudeColumn1);
        table1.addColumns(timeColumn1);
        table1.addColumns(dataColumn1);

        List<Table> tables = new LinkedList<>();
        tables.add(table);
        tables.add(table1);
        return tables;
    }
}

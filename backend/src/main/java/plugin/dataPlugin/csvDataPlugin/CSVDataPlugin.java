package plugin.dataPlugin.csvDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataPlugin implements DataPlugin {
    private static int PREDICTNUM = 5;

    @Override
    public String getName() {
        return "CSV Data Plugin";
    }

    @Override
    public String getIntro() {
        return "Handle Datasets in CSV Files, Group Data By Integer Part of Locations";
    }

    @Override
    public List<MyData> importDataFromFile(String path) {
        List<MyData> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            //Skip the header.
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double longtitude = Double.valueOf(values[6]);
                double latitude = Double.valueOf(values[5]);
                String[] date = values[2].split("-");
                long year = Long.valueOf(date[0]);
                double data = Double.valueOf(values[1]);
                records.add(new MyData(new Location((long)longtitude, (long)latitude), year, data));
            }
        } catch (Exception e) {
            System.out.println("Read file error! Reporting to frontend...");
            return null;
        }
        return records;
    }

    @Override
    public List<MyData> importDataFromAPI(String link) {
        return null;
    }

    @Override
    public boolean dataEqual(MyData d1, MyData d2) {
        return false;
    }

    @Override
    public MyData group(MyData d1, MyData d2) {
        return null;
    }

    @Override
    public int predictFuture() {
        return PREDICTNUM;
    }
}

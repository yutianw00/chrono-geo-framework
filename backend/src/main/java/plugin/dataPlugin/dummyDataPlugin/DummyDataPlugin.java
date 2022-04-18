package plugin.dataPlugin.dummyDataPlugin;

import framework.core.DataPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DummyDataPlugin implements DataPlugin {

    private static int PREDICTNUM = -1;

    @Override
    public String getName() {
        return "dummy Data";
    }

    @Override
    public String getIntro() {
        return "DATA dummy plugin for testing";
    }

    @Override
    public List importDataFromFile(String path) {
        MyData d1 = new MyData(new Location(10,10), 2010, 100);
        MyData d2 = new MyData(new Location(10,20), 2013, 200);
        MyData d3 = new MyData(new Location(10,30), 2010, 150);
        MyData d4 = new MyData(new Location(10,10), 2012, 150);
        MyData d5 = new MyData(new Location(10,50), 2012, 10);
        MyData d6 = new MyData(new Location(20,10), 2013, 120);
        MyData d7 = new MyData(new Location(30,10), 2010, 200);
        MyData d8 = new MyData(new Location(40,10), 2011, 800);
        MyData d9 = new MyData(new Location(50,10), 2011, 300);
        MyData d10 = new MyData(new Location(80,10), 2010, 100);
        MyData d11 = new MyData(new Location(10,10), 2010, 1000);

        return Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11);
    }

    @Override
    public List importDataFromAPI(String json) {
        return null;
    }

//    @Override
//    public boolean dataEqual(MyData d1, MyData d2) {
//        return d1.getLocation().equals(d2.getLocation()) && d1.getTime() == d2.getTime();
//    }
//
//    @Override
//    public MyData group(MyData d1, MyData d2) {
//        return new MyData( d1.getLocation(), d1.getTime(),d1.getData() + d2.getData());
//    }

    @Override
    public int predictFuture() {
        return PREDICTNUM;
    }
}

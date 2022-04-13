package framework.core;
import framework.core.utils.MyData;

import java.io.File;
import java.util.*;

public interface DataPlugin {

    public String getName();

    public String getIntro();

    public List<MyData> importDataFromFile(String path);

    public List<MyData> importDataFromAPI(String json);

    public boolean dataEqual(MyData d1, MyData d2);

    public MyData group(MyData d1, MyData d2);

//    /**
//     * The trans method is transform the raw file into an Integer List.
//     * Read the data and process it. Then group them and collect them to the List.
//     * There is dependency library we need to use to deal with the csv data.
//     * @param file input raw data
//     * @return List of Integer that meet the framework's requirement format.
//     */
//    public List<Integer> trans(File file);
//
//    /**
//     * Transfer the intermediate file data to the List the framework need.
//     * @param file the processed
//     * @return List of Integer that meet the framework input
//     */
//    public List<Integer> changeFile(File file);
//
//    /**
//     * Read the raw file data to File.
//     * @param file raw file
//     * @return File that have been processed.
//     */
//    public File readData(File file);
//
//    /**
//     * potential helper function to process and store the intermediate state.
//     */
//    public void helper();
//

}


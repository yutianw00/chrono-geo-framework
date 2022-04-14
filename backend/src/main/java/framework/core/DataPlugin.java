package framework.core;
import framework.core.utils.MyData;

import java.util.*;

public interface DataPlugin {

    /**
     * get the name for the data plugin
     *
     * @return The name for the data plugin
     */
    public String getName();

    /**
     * get a brief introduction for the data plugin
     *
     * @return a brief introduction for the data plugin
     */
    public String getIntro();

    /**
     * import the data from a file
     *
     * @param path The path of the file
     * @return The data from the file in form of {@link MyData} list,
     *  or {@code null} if importing data from a file is not supported
     */
    public List<MyData> importDataFromFile(String path);

    /**
     * import the data from a API
     *
     * @param link The link/token for the api
     * @return The data imported from the API in form of {@link MyData} list,
     *  or {@code null} if importing data from API is not supported
     */
    public List<MyData> importDataFromAPI(String link);

    /**
     * Decide if two data are considered equal (meaning,
     *  having the same/similar time, and same/similar geo location
     *  so that their data could be merged)
     *
     * @param d1 The first data
     * @param d2 The second data
     * @return {@code true} if they are considered similar and data could
     *  be merged, {@code false} otherwise
     */
    public boolean dataEqual(MyData d1, MyData d2);

    /**
     * Merge two equal/similar data (for example, adding the data together)
     * Prerequisite: {@code dataEqual(d1, d2)} returns true
     *
     * @param d1 The first data
     * @param d2 The second data
     * @return the resulting data of the merge
     */
    public MyData group(MyData d1, MyData d2);

}


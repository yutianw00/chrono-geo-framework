package framework.core;
import framework.core.utils.MyData;

import java.util.*;

public interface DataPlugin {

    /**
     * get the name for the data plugin
     *
     * @return The name for the data plugin
     */
    String getName();

    /**
     * get a brief introduction for the data plugin
     *
     * @return a brief introduction for the data plugin
     */
    String getIntro();

    /**
     * import the data from a file
     *
     * @param path The path of the file
     * @return The data from the file in form of {@link MyData} list,
     *  or {@code null} if importing data from a file is not supported,
     *  or if there's an error happened during reading file
     */
     List<MyData> importDataFromFile(String path);

    /**
     * import the data from a API
     *
     * @param link The link/token for the api
     * @return The data imported from the API in form of {@link MyData} list,
     *  or {@code null} if importing data from API is not supported,
     *  or if there's an error happened during reading API url
     */
     List<MyData> importDataFromAPI(String link);

    /**
     * Tell the framework what kind of data it is, so that
     * when the visual plugin is rendering the data, it could
     * create a suitable title
     * @return a brief description of the data field
     */
     String dataDescription();

    /**
     * tell the framework whether should predict future data or not,
     * using a Machine Learning ARIMA algorithm API; return an int:
     * if -1 means do not predict, otherwise tells how
     * many future data should be predicted
     *
     * @return if positive, means how many future data should
     *         be predicted; if -1, means should not predict
     */
     int predictFuture();

}


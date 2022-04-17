package framework.core;

import framework.gui.State;

public interface Framework {

    /**
     * sort the data in the framework with respect to time
     */
    void sortData();

    /**
     * group similar data together and reduce them to a new data
     * similar data is defined in {@see framework.core.Dataplugin#dataEqual()}
     * and are grouped following {@see framework.core.Dataplugin#group()}
     */
    void groupData();

    /**
     * choose a data plugin to use
     * @param id The index of the data plugin in the plugin list
     */
    void chooseDataPlugin(int id);

    /**
     * choose a visual plugin to use
     * @param id The index of the visual plugin in the plugin list
     */
    void chooseVisualPlugin(int id);

    /**
     * register the dataPlugin into the dataplugin list
     * @param dataPlugin The {@link DataPlugin} to register
     */
    void registerDataPlugin(DataPlugin dataPlugin);

    /**
     * register the visualPlugin into the dataplugin list
     * @param visualPlugin the {@link VisualPlugin} to register
     */
    void registerVisualPlugin(VisualPlugin visualPlugin);

    /**
     * clear all previous selections (e.g. selected dataplugin/visualplugin,
     *  error message, rendering results, etc.), and restart
     */
    void restart();

    /**
     * Import the data from API or a file;
     *  if from API, str is the API url;
     *  if from a file, str is the file path
     * @param str The API url or the file path
     */
    void importData(String str);

    /**
     * first, import the data using {@code importData} defined above by passing
     *  a str (API url or file path) to it; then, call the visualPlugin
     *  {@code render} function which do the rendering
     *  and opens a new webpage automatically showing the rendering results
     * @param str the input for {@code importData}, meaning the API url or
     *            the file path
     */
    void render(String str);

    /**
     * Get the state that can be passed to the frontend based on the current
     *  framework situation
     * @return the state representing the current framework state
     */
    State getState();
}

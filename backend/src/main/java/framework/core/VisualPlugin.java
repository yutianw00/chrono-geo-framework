package framework.core;

import framework.core.utils.MyData;

import java.util.*;

public interface VisualPlugin {

    /**
     * Get the name of the visual plugin
     *
     * @return the name of the current visual plugin
     */
    String getName();

    /**
     * Get a brief introduction of the visual plugin
     *
     * @return an introduction to the current visual plugin
     */
    String getIntro();

    /**
     * create a new html file (for example, index.html) in the folder,
     * and automatically open that rendered webpage, return the result of
     * rendering
     *
     * @param data the data take in for visualization to render
     * @return true if render succeed; false if render failed
     */
    boolean render(List<MyData> data);

    /**
     * get the title of the rendered visual plot
     *
     * @return the title of the rendered plot
     */
    String graphTitle();

    /**
     * get a description of the rendered visual plot
     *
     * @return a description of the rendered visual plot
     */
    String graphDescription();
}


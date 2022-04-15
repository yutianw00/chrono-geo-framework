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
     * Get the rendering frontend HTML codes for the visual plot
     *  from the data provided
     *
     * @param data The data provided (sorted and grouped) by the framework
     * @return the rendering html codes that will appear on the frontend
     *  the returning HTML codes need to be surrounded by a outer <div></div>
     */
    String render(List<MyData> data);

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


# Design Document

## Domain

The idea is provide a tool that can gather chronological data related to geography and maps, sorting the data in time order, and gathering them with geometry similarities, so that the difference of data related to geograph and their changes overtime could be visualized in an intuitive way. 

The framework's main jobs include sorting the data chronologically, dividing them by "time spans", and grouping and merging the data together with self-defined geology similarities.

This framework supports many real-world applications, from historic population migration to recent COVID distribution; from office locations of a company to geological environment changes over the world. 

### Supported Plugins
Two types of plugins are included in the framework: `Data Plugin`s and `Visualization Plugin`s. 

#### Data Plugins
`Data Plugin`s provide a way to convert a specific data source to the format that the framework could accept. To show chrnological changes while grouping data with geometrical similarities, the data passed into the framework should at least have the following fields: `time`, `geo-location` and `data` themselves. Meaning it should at least tell what the time it is, where the data are, and what's the value of the measurement.

Specifically, example plugins can be used to:
- CSV plugin that takes one or several CSV table and convert into the framework acceptable form
- Excel plugin that takes data from Microsoft Excel sheets
- Twitter pluginthat takes some generic Twitter messages

#### Visualization Plugins
The `Visual Plugin`s provide functionality to present the data in an intuitive way. They could include:

- A classic table that collects data and sort in chronological order;
- Geography heat map that displaying the change of density of the data from different locations over time;
- Several line charts, one for each place, showing the data changes over time for a specific place

## Generality vs Specificity

### Key Abstraction
In this framework, the key abstraction is the intermediate data type. By transforming data in CSV files, Excel files, Twitter messages or other formats into a same intermediate state, the handle part of this framework deal with different files without knowing the details, it can be generic enough to accept various kinds of data sources. Since visual plugins just need to process data in intermediate state, this framework can also provide multiple visualizations like GeoMap graph, PieChart graph and so on.  Besides, the main task of the framework focuses on sorting and gathering data based on geographic features, its functinoality covers from like population migration to recent COVID virus development, so this framework is also specific enough to be reused in many kinds of fields.

### Reusable Functionality
No matter in which format, all the input data need to be sorted in time order and gathered into groups based on geometry features, so the sort function and gather function will be the core reusable functions. Besides, there are some more generic functions can be reused, like set descriptions of data or visualizations, get the size of input data, start and restart and so on.

### Potential Flexibility of Plugins
At the beginning, the data plugins of this framework have covered CSV files, Excel files and Twitter messages, and visualization plugins of this framework have covered classic table, geography heat map and line charts. And this framework is open to provide plugins to support more kinds of data format and display format. And the main task of a plugin is to accept data in a specific type into a generic type (intermediate state), lots parts of other jobs will be done by reusable parts. In the future, the framework may give support to different ways to choose plugins like accepting terminal command lines, reading configuration files and so on.


## Project Structure

### Organization of framework 
The project is divided into frontend and backend.

The frontend is a single-page web application frontend, possibly using `React` framework, giving users an GUI interface to choose importing data format (choose `Data Plugin`) and choose visualization styles (`Visual Plugin`). The frontend file structure depends on the framework (`React`, `Vue`, etc.) we choose to implement. 

The backend is a Java project that is responsible for building the core functionalities, providing plugin interfaces and using plugins. 

### Plugin organization and interface locations

Below is a tentative backend project structure: 
```
|-- src
  - Main.java (class)
  |-- framework
    |--core
      - Framework.java (interface)
      - FrameworkImpl.java (class)
      - DataPlugin.java (interface)
      - VisualPlugin.java (interface)
    |--gui
  |-- plugin
    |-- dataPlugin
      |-- plugin1Folder
      |-- plugin2Folder
      ...
    |-- visualPlugin
      |-- plugin1Folder
      |-- plugin2Folder
      ...
|-- resources
  |-- META-INF.services
    - plugins
```

For convinience, an object model is also shown as below:

![domainModel](domainModel.jpg)

### Key data structure
The key data structure used are java `List`s and `Map`s.

The `List`s are used to store the plugins. A `List` of `DataPlugin` and a `List` of `VisualPlugin` would be included, and when the framework starts these plugins would be registered and called when necessary.

The `Map` are used to store and group the data. Data with similar/same geology locations are grouped together by `Map` or `Set`. The `Map` keys can be time or geology locations. If Necessary a java `TreeMap` can be used to sort the keys in chrnological order. 


### Plugins loaded
The plugins are loaded using java `ServiceLoader`. The paths for the plugin classes will be stored in the `plugins` file under the `resources/META-INF.services` and would be registered using the java `ServiceLoader` in the `Main` class. 

## Plugin interfaces

dataSet plugin interface
```
import java.io.File;
import java.util.List;

public interface DataPlugin {
    /**
     * The trans method is transform the raw file into an Integer List.
     * Read the data and process it. Then group them and collect them to the List.
     * There is dependency library we need to use to deal with the csv data.
     * @param file input raw data
     * @return List of Integer that meet the framework's requirement format.
     */
    public List<Integer> trans(File file);

    /**
     * Transfer the intermediate file data to the List the framework need.
     * @param file the processed
     * @return List of Integer that meet the framework input
     */
    public List<Integer> changeFile(File file);

    /**
     * Read the raw file data to File.
     * @param file raw file
     * @return File that have been processed.
     */
    public File readData(File file);

    /**
     * potential helper function to process and store the intermediate state.
     */
    public void helper();
}

```

CSVPlugin.java:
```
package edu.cmu.cs.cs214.rec10.plugin.dataPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.DataPlugin;

import java.io.File;
import java.util.List;

public class CSVPlugin implements DataPlugin {
    List<Integer> data;
    File CSVFile;
    List<Object> intermediateState;

    /**
     * The trans method is transform the cvs file into an Integer List.
     * Read the data and process it. Then group them and collect them to the List.
     * There is dependency library we need to use to deal with the csv data.
     * @param file input csv data
     * @return List of Integer that meet the framework's requirement format.
     */
    public List<Integer> trans(File file) {
        this.CSVFile = readData(file);
        List<Integer> temp = changeFile(this.CSVFile);
        return temp;
    }

    /**
     * Transfer the CVS file data to the List the framework need.
     * @param csvFile the processed CVSFile
     * @return List of Integer that meet the framework input
     */
    public List<Integer> changeFile(File csvFile) {
        return null;
    }

    /**
     * Read the raw CSV file data to this.CSV File.
     * @param file raw CVS file
     * @return File that have been processed.
     */
    public File readData(File file) {
        return null;
    }

    /**
     * helper function for potential using when transform data.
     */
    public void helper() {
        return;
    }
}

```

ExcelPlugin.java:
```
package edu.cmu.cs.cs214.rec10.plugin.dataPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.DataPlugin;

import java.io.File;
import java.util.List;

public class ExcelPlugin implements DataPlugin {
    List<Integer> data;
    File ExcelFile;
    List<Object> intermediateState;

    /**
     * The trans method is transform the Excel file into an Integer List.
     * Read the data and process it. Then group them and collect them to the List.
     * There is dependency library we need to use to deal with the csv data.
     * @param file input Excel data
     * @return List of Integer that meet the framework's requirement format.
     */
    public List<Integer> trans(File file) {
        this.ExcelFile = readData(file);
        List<Integer> temp = changeFile(this.ExcelFile);
        return temp;
    }

    /**
     * Transfer the Excel file data to the List the framework need.
     * @param excelFile the processed Excel File
     * @return List of Integer that meet the framework input
     */
    public List<Integer> changeFile(File excelFile) {
        return null;
    }

    /**
     * Read the raw Excel file data to this.Excel File.
     * @param file raw Excel file
     * @return File that have been processed.
     */
    public File readData(File file) {
        return null;
    }

    /**
     * potential helper function to process and store the intermediate state.
     */
    public void helper() {
        return;
    }

}

```

TwitterPlugin.java:
```
package edu.cmu.cs.cs214.rec10.plugin.dataPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.DataPlugin;

import java.io.File;
import java.util.List;

public class TwitterPlugin implements DataPlugin {
    List<Integer> data;
    File twitterFile;
    List<Object> intermediateState;

    /**
     * The trans method is transform the Twitter data file into an Integer List.
     * Read the data and process it. Then group them and collect them to the List.
     * There is dependency library we need to use to deal with the csv data.
     * @param file input Twitter data
     * @return List of Integer that meet the framework's requirement format.
     */
    public List<Integer> trans(File file) {
        this.twitterFile = readData(file);
        List<Integer> temp = changeFile(this.twitterFile);
        return temp;
    }

    /**
     * Transfer the Twitter file data to the List the framework need.
     * @param twitterFile the processed Excel File
     * @return List of Integer that meet the framework input
     */
    public List<Integer> changeFile(File twitterFile) {
        return null;
    }

    /**
     * Read the raw Twitter file data to this.Twitter File.
     * @param file raw Twitter file
     * @return File that have been processed.
     */
    public File readData(File file) {
        return null;
    }

    /**
     * potential helper function to process and store the intermediate state.
     */
    public void helper() {
        return;
    }

}

```

Visualization Plugin interface: 
```
package edu.cmu.cs.cs214.rec10.framework.core;

import java.util.List;

public interface VisualizationPlugin {
    /**
     * Transform the output data from the framework to be ready to render in the front-end.
     * Store the answer to this.outPut
     * @param input input from the framework
     */
    public void transform(List<Integer> input);

    /**
     * First step to transform the data from the framework to the intermediateState.
     * @param input
     * @return
     */
    public List<Integer> tranData(List<Integer> input);

    /**
     * Second step to transform the data into suitable state that can be render in the front end.
     * @param intermediateState intermediate state of output
     * @return List of String to be render in the front-end
     */
    public List<String> outPutData(List<Integer> intermediateState);

    /**
     * Potential transformation of intermediate state.
     */
    public void helper();
}

```

GeoMapPlugin:
```
package edu.cmu.cs.cs214.rec10.plugin.VisualizationPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.VisualizationPlugin;

import java.util.List;

public class GeoMapPlugin implements VisualizationPlugin {
    private Object map;
    private List<Integer> data;
    private List<String> outPut;
    private List<Integer> intermediateState;

    /**
     * Transform the output data from the framework to be ready to render in the front-end.
     * Store the answer to this.outPut
     * @param input input from the framework
     */
    public void transform(List<Integer> input) {
        this.intermediateState = tranData(input);
        this.outPut = outPutData(this.intermediateState);
    }

    /**
     * First step to transform the data from the framework to the intermediateState.
     * @param input
     * @return
     */
    public List<Integer> tranData(List<Integer> input) {
        return null;
    }

    /**
     * Second step to transform the data into suitable state that can be render in the front end.
     * @param intermediateState intermediate state of output
     * @return List of String to be render in the front-end
     */
    public List<String> outPutData(List<Integer> intermediateState) {
        return null;
    }

    /**
     * Potential transformation of intermediate state.
     */
    public void helper() {
        return;
    }
}

```

PieChartPlugin:
```
package edu.cmu.cs.cs214.rec10.plugin.VisualizationPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.VisualizationPlugin;

import java.util.List;

public class PieChartPlugin implements VisualizationPlugin {
    private Object PieChart;
    private List<Integer> data;
    private List<String> outPut;
    private List<Integer> intermediateState;

    /**
     * Transform the output data from the framework to be ready to render in the front-end.
     * Store the answer to this.outPut
     * @param input input from the framework
     */
    public void transform(List<Integer> input) {
        this.intermediateState = tranData(input);
        this.outPut = outPutData(this.intermediateState);
    }

    /**
     * First step to transform the data from the framework to the intermediateState.
     * @param input
     * @return
     */
    public List<Integer> tranData(List<Integer> input) {
        return null;
    }

    /**
     * Second step to transform the data into suitable state that can be render in the front end.
     * @param intermediateState intermediate state of output
     * @return List of String to be render in the front-end
     */
    public List<String> outPutData(List<Integer> intermediateState) {
        return null;
    }

    /**
     * Potential transformation of intermediate state.
     */
    public void helper() {
        return;
    }
}

```

XYMapPlugin
```
package edu.cmu.cs.cs214.rec10.plugin.VisualizationPlugin;

import edu.cmu.cs.cs214.rec10.framework.core.VisualizationPlugin;

import javax.management.ValueExp;
import java.util.List;

public class XYMapPlugin implements VisualizationPlugin {
    private Object XYMapChart;
    private List<Integer> data;
    private List<String> outPut;
    private List<Integer> intermediateState;

    /**
     * Transform the output data from the framework to be ready to render in the front-end.
     * Store the answer to this.outPut
     * @param input input from the framework
     */
    public void transform(List<Integer> input) {
        this.intermediateState = tranData(input);
        this.outPut = outPutData(this.intermediateState);
    }

    /**
     * First step to transform the data from the framework to the intermediateState.
     * @param input
     * @return
     */
    public List<Integer> tranData(List<Integer> input) {
        return null;
    }

    /**
     * Second step to transform the data into suitable state that can be render in the front end.
     * @param intermediateState intermediate state of output
     * @return List of String to be render in the front-end
     */
    public List<String> outPutData(List<Integer> intermediateState) {
        return null;
    }

    /**
     * Potential transformation of intermediate state.
     */
    public void helper() {
        return;
    }
}

```

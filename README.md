# HW6 README

## Basic Information
- Name: Chrono-geo data visualizer
- Group members: Xupeng Shi, Yutian Wang, Yihang Liu
- Group name: remote-city

## Building and running the project

### Building and running the frontend
The frontend is written in Typescript using `npm`. To build, go to the `frontend/` directory and run
```
npm install
npm run start
```

### Building and running the backend
The backend is written in Java using Maven. To build, go to the `backend/` and run 
```
mvn exec:exec
```

Alternatively, open the folder `backend/` in Intellj, go to the `backend/src/main/java/App.java` file and click the "run" icon to run that class. 

## Framework Description
This framework provides a tool that can gather chronological data related to geography and maps, and sorting the data in time order, and could also optionally make using the Machine Learning ARIMA (Autoregressive integrated moving average) algorithm to predict future data, so that the difference of data related to geograph and their changes overtime could be visualized in an intuitive way, and also future predictions can be provided.

The framework's main jobs include 
- calling the user-selected data plugin to import the data
- sorting the data chronologically and make predictions of the data using the ARIMA algorithm
- using a user-selected visual plugin to display the data and predictions 

This framework supports data with time and location, so it can be applied to many real-world applications: from historic population migration to recent COVID distribution; from office locations of a company to geological environment changes over the world. 


## Start and extend the framework
As mentioned above, to start the framework, first run the backend using intellj or `mvn exec:exec`, then run the frontend using `npm run start`. Please note that the framework uses http to communicate between the frontend and backend, and are using ports `3000` and `8080`. Please make sure no other applications are using these ports before running the framework.

### Adding new data plugin
- Create a new folder under `/src/main/java/plugin/dataPlugin/`, create a new class that implements the `/src/main/java/framework/core/DataPlugin.java` interface
- add your class path as a new line in the `backend/src/main/resources/META-INF/services/framework.core.DataPlugin`

Save your changes, and then your data plugin would be successfully added!

### Adding new visual plugin
- Create a new folder under `/src/main/java/plugin/visualPlugin/`, create a new class that implements the `/src/main/java/framework/core/VisualPlugin.java` interface
- add your class path as a new line in the `backend/src/main/resources/META-INF/services/framework.core.VisualPlugin`

Save your changes, and then your visual plugin would be successfully added!

For more information about the `DataPlugin.java` and `VisualPlugin.java` interfaces, please check the next section `Interface documentation`. 

## Interface documentation

### `DataPlugin.java` interface Documentation

##### `String getName()`

get the name for the data plugin

 * **Returns:** The name for the data plugin

##### `String getIntro()`

get a brief introduction for the data plugin

 * **Returns:** a brief introduction for the data plugin

##### `List<MyData> importDataFromFile(String path)`

import the data from a file

 * **Parameters:** `path` — The path of the file
 * **Returns:** The data from the file in form of {@link MyData} list,

     or {@code null} if importing data from a file is not supported,

     or if there's an error happened during reading file

##### `List<MyData> importDataFromAPI(String link)`

import the data from a API

 * **Parameters:** `link` — The link/token for the api
 * **Returns:** The data imported from the API in form of {@link MyData} list,

     or {@code null} if importing data from API is not supported,

     or if there's an error happened during reading API url

##### `String dataDescription()`

Tell the framework what kind of data it is, so that when the visual plugin is rendering the data, it could create a suitable title

 * **Returns:** a brief description of the data field

##### `int predictFuture()`

tell the framework whether should predict future data or not, using a Machine Learning ARIMA algorithm API; return an int: if -1 means do not predict, otherwise tells how many future data should be predicted

 * **Returns:** if positive, means how many future data should

     be predicted; if -1, means should not predict


### `VisualPlugin.java` interface Documentation

##### `String getName()`

Get the name of the visual plugin

 * **Returns:** the name of the current visual plugin

##### `String getIntro()`

Get a brief introduction of the visual plugin

 * **Returns:** an introduction to the current visual plugin

##### `boolean render(List<MyData> data, String dataDescription)`

create a new html file (for example, index.html) in the folder, and automatically open that rendered webpage, return the result of the rendering process

 * **Parameters:** `data` — the data take in for visualization to render
 * **Returns:** true if the render succeed, false otherwise

##### `String graphTitle()`

get the title of the rendered visual plot

 * **Returns:** the title of the rendered plot

##### `String graphDescription()`

get a description of the rendered visual plot

 * **Returns:** a description of the rendered visual plot


## Machine Learning API description

The framework uses the Machine Learning ARIMA (Autoregressive integrated moving average) algorithm to make future predictions. Different from the popular time-series prediction deep learning recurrent neural network models such as LSTM or GRU, ARIMA is a relatively light and simple model that relies on optimization and statistics, and can be run locally. No deep neural network is needed in this network, so no extensive data or training is needed, allowing the framework to make machine learning based predictions relatively fast with relative smaller dataset. Only several linear computations over the dataset would be needed before the prediction is made. 

For more information about ARIMA (Autoregressive integrated moving average), please [check Wikipedia](https://en.wikipedia.org/wiki/Autoregressive_integrated_moving_average), [click here](https://machinelearningmastery.com/arima-for-time-series-forecasting-with-python/), or [click here](https://www.machinelearningplus.com/time-series/arima-model-time-series-forecasting-python/).

The framework uses a local ARIMA api developed by Workday.com. For respective Javadoc and Github page, please check below. 
- [Javadoc API](https://www.mvndoc.com/c/com.workday/timeseries-forecast/index.html)
- [Github page](https://github.com/Workday/timeseries-forecast)

## Example local dataset and APIs

### Local dataset

Two example local dataset, `bird_migration.csv` and `bird_migration.xlsx` are provided, and they are stored at `backend/src/main/resources/dataset`.

For testing or debugging purposes, their "mini version" (which is a small file based on the first 30 lines of the dataset), two other local datasets are also provided: `bird_migration_mini.csv` and `bird_migration_mini.xlsx`, also stored at the same folder. 

### APIs
The api we used to test our implementation is stored in `api.txt`, also in the folder `backend/src/main/resources/dataset`. 

## Example plugins and corresponding screenshots

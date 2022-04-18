# HW6

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
This framework provides a tool that can gather chronological data related to geography and maps, and sorting the data in time order, and could also optionally make using the Machine Learning ARIMA algorithm to predict future data, so that the difference of data related to geograph and their changes overtime could be visualized in an intuitive way, and also future predictions can be provided.

The framework's main jobs include 
- calling the user-selected data plugin to import the data
- sorting the data chronologically and make predictions of the data using the ARIMA algorithm
- using a user-selected visual plugin to display the data and predictions 

This framework supports data with time and location, so it can be applied to many real-world applications: from historic population migration to recent COVID distribution; from office locations of a company to geological environment changes over the world. 


## Start and extend the framework


## Interface documentation

## Machine Learning API description

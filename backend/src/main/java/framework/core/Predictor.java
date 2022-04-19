package framework.core;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import framework.core.utils.Location;
import framework.core.utils.MyData;

import java.util.Arrays;
import java.util.List;

public class Predictor {

    private static final int p = 3;
    private static final int d = 0;
    private static final int q = 3;
    private static final int P = 1;
    private static final int D = 1;
    private static final int Q = 0;
    private static final int m = 0;

    public static void predict(List<MyData> dataLst, int predictNum) {
        if (predictNum < 0) {
            return;
        }

        double[] longtitudeArray = new double[(dataLst.size())];
        double[] latitudeArray = new double[(dataLst.size())];
        double[] dataArray = new double[(dataLst.size())];
        double[] timeArray = new double[(dataLst.size())];

        for (int i = 0; i < dataLst.size(); i++) {
            MyData data = dataLst.get(i);
            longtitudeArray[i] = data.getLocation().getLongtitude();
            latitudeArray[i] = data.getLocation().getLatitude();
            dataArray[i] = data.getData();
            timeArray[i] = data.getTime();
        }

        double[] predictedLongtitude = predictData(longtitudeArray, predictNum);
        double[] predictedLatitude = predictData(latitudeArray, predictNum);
        double[] predictedData = predictData(dataArray, predictNum);
        double[] predictedTime = predictData(timeArray, predictNum);

        for (int i = 0; i < predictNum; i++) {
            MyData predictData = new MyData(new Location(predictedLongtitude[i],
                    predictedLatitude[i]), (long)predictedTime[i], predictedData[i]);
            dataLst.add(predictData);
        }
    }

    public static double[] predictData(double[] data, int forecastSize) {
        ForecastResult forecastResult = Arima.forecast_arima(data, forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

        // Read forecast values
        double[] forecastData = forecastResult.getForecast();
        return forecastData;
    }

}

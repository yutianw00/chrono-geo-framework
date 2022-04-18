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

        System.out.println(dataLst.size());
        System.out.println(dataLst);

        for (int i = 0; i < dataLst.size(); i++) {
            System.out.println(i);
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
            MyData predictData = new MyData(new Location((long)predictedLongtitude[i],
                    (long)predictedLatitude[i]), (long)predictedTime[i], predictedData[i]);
            dataLst.add(predictData);
        }
    }

    public static double[] predictData(double[] data, int forecastSize) {
        ForecastResult forecastResult = Arima.forecast_arima(data, forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

        // Read forecast values
        double[] forecastData = forecastResult.getForecast();
        return forecastData;
    }

    public static void main(String[] args) {
        // Prepare input timeseries data.
//        double[] dataArray = new double[] {1,2,3,4,5,6,7,8,9};
        double[] dataArray = new double[] {10.0, 30.0, 80.0, 10.0, 40.0, 50.0, 10.0, 10.0, 10.0, 20.0};

        // Set ARIMA model parameters.
        int p = 3;
        int d = 0;
        int q = 3;
        int P = 1;
        int D = 1;
        int Q = 0;
        int m = 0;
        int forecastSize = 5;

        // Obtain forecast result. The structure contains forecasted values and performance metric etc.
        ForecastResult forecastResult = Arima.forecast_arima(dataArray, forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

        // Read forecast values
        double[] forecastData = forecastResult.getForecast(); // in this example, it will return { 2 }
        System.out.println(forecastData.length);
        System.out.println(Arrays.toString(forecastData));

        // You can obtain upper- and lower-bounds of confidence intervals on forecast values.
        // By default, it computes at 95%-confidence level. This value can be adjusted in ForecastUtil.java
        double[] uppers = forecastResult.getForecastUpperConf();
        double[] lowers = forecastResult.getForecastLowerConf();

        // You can also obtain the root mean-square error as validation metric.
        double rmse = forecastResult.getRMSE();

        // It also provides the maximum normalized variance of the forecast values and their confidence interval.
        double maxNormalizedVariance = forecastResult.getMaxNormalizedVariance();

        // Finally you can read log messages.
        String log = forecastResult.getLog();
    }
}

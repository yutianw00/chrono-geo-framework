package framework.core;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

public class ArimaPrediction {
    public static void main(String[] args) {
        // Prepare input timeseries data.
        double[] dataArray = new double[] {1,2,3,4,5,6,7,8,9,10};

        // Set ARIMA model parameters.
        int p = 3;
        int d = 0;
        int q = 3;
        int P = 1;
        int D = 1;
        int Q = 0;
        int m = 0;
        int forecastSize = 1;

        // Obtain forecast result. The structure contains forecasted values and performance metric etc.
        ForecastResult forecastResult = Arima.forecast_arima(dataArray, forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

        // Read forecast values
        double[] forecastData = forecastResult.getForecast(); // in this example, it will return { 2 }
        System.out.println(forecastData.length);
        System.out.println(forecastData[0]);

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

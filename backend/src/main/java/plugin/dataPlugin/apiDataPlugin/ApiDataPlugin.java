package plugin.dataPlugin.apiDataPlugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import framework.core.DataPlugin;
import framework.core.utils.Location;
import framework.core.utils.MyData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiDataPlugin implements DataPlugin {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getIntro() {
        return null;
    }

    @Override
    public List<MyData> importDataFromFile(String path) {
        return null;
    }

    @Override
    public List<MyData> importDataFromAPI(String link) {
        List<MyData> records = new ArrayList<>();

        StringBuffer jsonBuffer = new StringBuffer();
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode" + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    jsonBuffer.append(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonBuffer.toString();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        int id = 0;
        String jsonID = id + "";
        while (jsonObject.containsKey(jsonID)) {
            JSONObject patient = (JSONObject) jsonObject.get(jsonID);
//            JSONArray routes = (JSONArray) patient.get("route");
//            JSONObject firstRoute = (JSONObject) routes.get(0);
//            String str = firstRoute.getString("start_time");
//            String year = str.split("-")[0];
            JSONObject patientInfo = (JSONObject) patient.get("patient_info");
            JSONObject residentalInfo = (JSONObject) patientInfo.get("residental_info");
            double longitude = Double.parseDouble(residentalInfo.getString("lng"));
            double latitude = Double.parseDouble(residentalInfo.getString("lat"));
            double data = Double.parseDouble(patientInfo.getString("age"));
//            System.out.println(longitude + " " + latitude + " " + age);
            records.add(new MyData(new Location((long) longitude, (long) latitude), Long.parseLong(jsonID), data));
            id++;
            jsonID = id + "";
        }
        return records;
    }

    @Override
    public boolean dataEqual(MyData d1, MyData d2) {
        return false;
    }

    @Override
    public MyData group(MyData d1, MyData d2) {
        return null;
    }
}

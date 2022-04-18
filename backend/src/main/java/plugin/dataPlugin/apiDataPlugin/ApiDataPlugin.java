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
    private static int PREDICTNUM = -1;

    @Override
    public String getName() {
        return "API";
    }

    @Override
    public String getIntro() {
        return "Download DataSet from URL";
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
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String jsonString = jsonBuffer.toString();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        int id = 0;
        String jsonID = id + "";
        while (jsonObject.containsKey(jsonID)) {
            JSONObject patient = (JSONObject) jsonObject.get(jsonID);
            Object obj = patient.get("route");
            String time = "";
            if (obj instanceof JSONArray) {
                JSONArray routes = (JSONArray) obj;
                JSONObject firstRoute = (JSONObject) routes.get(0);
                String str = firstRoute.getString("start_time");
                String[] arr = str.split("-");
                if (arr.length < 3) {
                    id++;
                    jsonID = id + "";
                    continue;
                }
                time = arr[0] + arr[1] + arr[2].split("T")[0];
            } else if (obj instanceof JSONObject) {
                JSONObject route = (JSONObject) obj;
                String str = route.getString("start_time");
                String[] arr = str.split("-");
                if (arr.length < 3) {
                    id++;
                    jsonID = id + "";
                    continue;
                }
                time = arr[0] + arr[1] + arr[2].split("T")[0];
            }
            JSONObject patientInfo = (JSONObject) patient.get("patient_info");
            JSONObject residentalInfo = (JSONObject) patientInfo.get("residental_info");
            double longitude = Double.parseDouble(residentalInfo.getString("lng"));
            double latitude = Double.parseDouble(residentalInfo.getString("lat"));
            double data = Double.parseDouble(patientInfo.getString("age"));
            records.add(new MyData(new Location((long) longitude, (long) latitude), Long.parseLong(time), data));
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

    @Override
    public int predictFuture() {
        return PREDICTNUM;
    }
}

package plugin.dataPlugin.apiDataPlugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        StringBuffer jsonBuffer = new StringBuffer();
        try {
            URL url = new URL("https://storage.googleapis.com/kagglesdsdata/datasets/544367/993325/Busan_Patient_Path.json?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=gcp-kaggle-com%40kaggle-161607.iam.gserviceaccount.com%2F20220416%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20220416T182217Z&X-Goog-Expires=259199&X-Goog-SignedHeaders=host&X-Goog-Signature=26ad16b03455af1f24ecb53174c1c52f105ad607eac585623848618bd3388f292bcde7dcecc9ea4a724ad1f4c4dc0d29fc03fb7e465983064778cdc8098126d23ff0938751949ab7e7d5980b606260df07788818bb95819f44727ff5b108ec11a3744ca91c96d4e1e2b8fd73b7e3e4fd409d2d25de011f1ad6c538b1f9db1144a79a5038cf4b3dbcdf5e05d2f0e07d47e3a13fc4942dffc34e6394905dba735e2396ea67290368410f088f80e58547faeeeec6422d357e914f38b56002bf832cb031040ab9836ef07b067463c7647c01475e49b0b364e140cec80e6525b29cd7f636b609c604dd6fd1ad5edebbdc6eec3bb3d805f31ae5169e9ec74150c71eda");
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
//                    System.out.println(scanner.nextLine());
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
            System.out.println(id);
            JSONObject patient = (JSONObject) jsonObject.get(jsonID);
//            JSONArray routes = (JSONArray) patient.get("route");
//            JSONObject firstRoute = (JSONObject) routes.get(0);
//            String str = firstRoute.getString("start_time");
//            String year = str.split("-")[0];
            JSONObject patientInfo = (JSONObject) patient.get("patient_info");
            JSONObject residentalInfo = (JSONObject) patientInfo.get("residental_info");
            Double longitude = Double.parseDouble(residentalInfo.getString("lng"));
            Double latitude = Double.parseDouble(residentalInfo.getString("lat"));
            long age = Long.parseLong(patientInfo.getString("age"));
            System.out.println(longitude + " " + latitude + " " + age);
            id++;
            jsonID = id + "";
        }
//        System.out.println(jsonObject.get("0"));
//        JSONObject jsonObject1 = (JSONObject) jsonObject.get("0");
//        JSONArray routes = (JSONArray) jsonObject1.get("route");
//        JSONObject firstRoute = (JSONObject) routes.get(0);
//        String str = firstRoute.getString("start_time");
//        String year = str.split("-")[0];
//        System.out.println(year);

//        System.out.println(jsonObject1.get("patient_info"));
//        JSONObject jsonObject2 = (JSONObject) jsonObject1.get("patient_info");
//        JSONObject jsonObject3 = (JSONObject) jsonObject2.get("residental_info");
//
//        System.out.println(jsonObject3.getString("lng"));

    }
}

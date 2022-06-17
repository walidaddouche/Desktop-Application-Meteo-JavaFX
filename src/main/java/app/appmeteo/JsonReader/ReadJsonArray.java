package app.appmeteo.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@SuppressWarnings("All") // ©Enlever ce code quand vous modifier le projet !
public class ReadJsonArray {
    String path;
    JSONObject adress;

    public ReadJsonArray(String path) throws IOException, ParseException {
        this.path = path;
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
        this.adress = (JSONObject) jsonArray.get(0);
    }

    public Object ReadJsonListObject(String parameter) {
        return adress.get(parameter);
    }

    public ReadJsonArray(String path, int day) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("daily");
        this.adress = (JSONObject) jsonArray.get(day);
    }

    public String getDesription() {
        JSONObject jsonObject1 = this.adress;
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("weather");
        JSONObject o = (JSONObject) jsonArray1.get(0);
        String weatherDescription = (String) o.get("description");
        return weatherDescription;
    }

    public Object getTemp() {
        JSONObject jsonObject1 = this.adress;
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("weather");
        JSONObject o2 = (JSONObject) jsonObject1.get("temp");
        Object temperature = o2.get("day");
        return temperature.toString();

    }

    public String getICon() {
        JSONArray jsonArray1 = (JSONArray) this.adress.get("weather");
        JSONObject o = (JSONObject) jsonArray1.get(0);
        String iconId = (String) o.get("icon");
        String icon = "http://openweathermap.org/img/w/" + iconId + ".png";
        return icon;
    }

    public static void main(String[] args) throws IOException, ParseException {
        ReadJsonArray readJsonArray = new ReadJsonArray("src/main/java/app/appmeteo/JsonReader/exemple JSON/PARISdaily.json", 1);
        System.out.println(readJsonArray.getTemp());
    }


}


package app.appmeteo.JsonReader;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@SuppressWarnings("All") // ©Enlever ce code quand vous modifier le projet !
public class ReadJsonList {
    String path;
    JSONObject jsonObject;


    public ReadJsonList(String path) throws IOException, ParseException {
        this.path = path;
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = parser.parse(fileReader);
        this.jsonObject = (JSONObject) obj;

    }

    public Object getcoord(String parameter) {
        JSONObject adress = (JSONObject) this.jsonObject.get("coord");
        return adress.get(parameter);
    }

    public Object getWind(String parameter) {
        JSONObject address = (JSONObject) jsonObject.get("wind");
        return address.get(parameter);
    }

    public Object getMain(String parameter) {
        JSONObject address = (JSONObject) jsonObject.get("main");
        return address.get(parameter);
    }

    public Object getSys(String parameter) {
        JSONObject address = (JSONObject) jsonObject.get("sys");
        return address.get(parameter);
    }

    public Object getClouds(String parameter) {
        JSONObject address = (JSONObject) jsonObject.get("clouds");
        return address.get(parameter);
    }
}

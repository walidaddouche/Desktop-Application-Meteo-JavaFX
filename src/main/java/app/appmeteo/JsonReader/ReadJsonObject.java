package app.appmeteo.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@SuppressWarnings("All") // ©Enlever ce code quand vous modifier le projet !
public class ReadJsonObject {
    String path;
    JSONObject jsonObject;


    public ReadJsonObject(String path) throws IOException, ParseException {
        this.path = path;
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = parser.parse(fileReader);
        this.jsonObject = (JSONObject) obj;


    }

    public Object ReadJsonObj(String parameter) {
        return jsonObject.get(parameter);
    }
}

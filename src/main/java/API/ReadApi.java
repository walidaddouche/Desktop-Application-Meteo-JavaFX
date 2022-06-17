package API;

import app.appmeteo.JsonReader.ReadJsonArray;
import app.appmeteo.JsonReader.ReadJsonList;
import app.appmeteo.JsonReader.ReadJsonObject;
import com.google.gson.Gson;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("All") // ©Enlever ce code quand vous modifier le projet !
public class ReadApi {
    public static void readJSON(String ville) throws IOException, ParseException {
        String url1 = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=c1550e47032f753476993a9f6ba80934&units=metric&lang=fr", ville.toUpperCase());
        URL url = new URL(url1);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        InputStream stream = httpConnection.getInputStream();
        BufferedReader scanner = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = scanner.readLine();
        while (line != null) {
            sb.append(line);
            line = scanner.readLine();
        }
        BufferedWriter b = new BufferedWriter(new FileWriter("src/main/java/app/appmeteo/JsonReader/exemple JSON/" + ville.toUpperCase() + ".json"));
        b.append(sb.toString());
        b.close();
    }

    public static void readJSONDaily(String ville) throws IOException, ParseException {
        ReadJsonList readJsonList = new ReadJsonList("src/main/java/app/appmeteo/JsonReader/exemple JSON/" + ville.toUpperCase() + ".json");
        Object string1 = readJsonList.getcoord("lon");
        Object string2 = readJsonList.getcoord("lat");
        String url1 = ("https://api.openweathermap.org/data/2.5/onecall?lat=" + string2 + "&" + "lon=" + string1 + "&exclude=day&appid=c1550e47032f753476993a9f6ba80934&units=metric&lang=fr");
        URL url = new URL(url1);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        InputStream stream = httpConnection.getInputStream();
        BufferedReader scanner = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = scanner.readLine();
        while (line != null) {
            sb.append(line);
            line = scanner.readLine();
        }
        BufferedWriter b = new BufferedWriter(new FileWriter("src/main/java/app/appmeteo/JsonReader/exemple JSON/" + ville.toUpperCase() + "daily" + ".json"));
        b.append(sb.toString());
        b.close();
    }
}

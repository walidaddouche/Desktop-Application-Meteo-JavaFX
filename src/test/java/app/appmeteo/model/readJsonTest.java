package app.appmeteo.model;

import app.appmeteo.JsonReader.ReadJsonArray;
import app.appmeteo.JsonReader.ReadJsonList;
import app.appmeteo.JsonReader.ReadJsonObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;


class ReadJsonTest {
    String pathTest = "src/main/java/app/appmeteo/JsonReader/exemple JSON/weather_marseille.json";

    @Test
    public void readJsonTest() throws IOException, ParseException {
        String expectedWeatherDescription = "scattered clouds";
        Object expectedTemperature = 277.86;
        Object expectedWindSpeed = 4.92;
        long exceptedTimezone = 3600;
        Object expectedLongitude = 5.5;
        Object exceptedLatitude = 43.3333;
        long exceptedClouds = 43;
        String expectedSysCountry ="FR";
        ReadJsonArray readJsonArray = new ReadJsonArray(pathTest);
        ReadJsonList readJsonList = new ReadJsonList(pathTest);
        ReadJsonObject readJsonObject = new ReadJsonObject(pathTest);


        String weather = (String) readJsonArray.ReadJsonListObject("description");
        long timezone = (Long) readJsonObject.ReadJsonObj("timezone");
        Object temperature =  readJsonList.getMain("temp" );
        Object windSpeed =  readJsonList.getWind("speed");
        Object longitude =  readJsonList.getcoord("lon");
        Object latitude =  readJsonList.getcoord("lat");
        String sysCountry = (String) readJsonList.getSys("country");
        long clouds =  (Long) readJsonList.getClouds("all");


        assertEquals(expectedWeatherDescription,weather);
        assertEquals(expectedTemperature,temperature);
        assertEquals(expectedWindSpeed,windSpeed);
        assertEquals(exceptedTimezone,timezone);
        assertEquals(expectedLongitude,longitude);
        assertEquals(exceptedLatitude,latitude);
        assertEquals(expectedSysCountry,sysCountry);
        assertEquals(exceptedClouds,clouds);
    }

}


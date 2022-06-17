package app.appmeteo;

import API.ReadApi;
import app.appmeteo.JsonReader.ReadJsonArray;
import app.appmeteo.JsonReader.ReadJsonList;
import app.appmeteo.JsonReader.ReadJsonObject;
import app.appmeteo.controller.AppMeteoController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("All")// ©Enlever ce code quand vous modifier le projet !
public class AppMeteoCLI {
    public static String getRouge() {
        return "\033[31m";
    }

    public static String getVert() {
        return "\033[32m";
    }

    public static String getJaune() {
        return "\033[33m";
    }

    public static String getBleu() {
        return "\033[34m";
    }

    public static String getPURPLE() {
        return "\u001B[35m";
    }

    public static String ResetColor() {
        return "\033[0m";
    }


    public static void CliTerminal() throws IOException, ParseException {
        Object temperature;
        Object tempMin;
        Object tempMax;
        Object windSpeed;
        Object pressure;
        Object humidity;
        String weather;

        //System.out.println("You requested command '" +args[0] + "' with parameter '" + args[1] + "'");
        System.out.println("\n Entrez le nom de la ville que vous souhaitez : ");

        try {
            Scanner scanner = new Scanner(System.in);
            String Ville = scanner.nextLine();
            ReadApi.readJSON(Ville.toUpperCase());
            scanner.close();


            String filename = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + Ville.toUpperCase() + ".json";
            ReadJsonList readJsonList = new ReadJsonList(filename);
            ReadJsonObject readJsonObject = new ReadJsonObject(filename);
            ReadJsonArray readJsonArray = new ReadJsonArray(filename);

            /** API **/
            /** api.openweathermap.org/data/2.5/weather?q=Paris&appid=c1550e47032f753476993a9f6ba80934 **/

            System.out.println(Ville + " :" + getBleu());
            System.out.println(getJaune() + "---------------------------------------------");


            temperature = readJsonList.getMain("temp");
            weather = (String) readJsonArray.ReadJsonListObject("description");
            tempMax = readJsonList.getMain("temp_min");
            tempMin = readJsonList.getMain("temp_max");
            windSpeed = readJsonList.getWind("speed");
            pressure = readJsonList.getMain("pressure");
            humidity = readJsonList.getMain("humidity");

            System.out.println("Temperature :" + Math.round(Float.parseFloat(String.valueOf(temperature))) + "°C" + getJaune());

            System.out.println("Description : " + weather + getJaune());
            System.out.println("Temperature Min  : " + tempMin + " ºC" + getJaune());
            System.out.println("Temperature Max  : " + tempMax + " ºC" + getJaune());
            System.out.println("Vitesse du  vent : " + windSpeed + " KM/H" + getJaune());
            System.out.println("Pression         : " + pressure + " Pa" + getJaune());
            System.out.println("humidité         : " + humidity + " %" + getJaune());
            System.out.println("---------------------------------------------------");
            System.out.println(getPURPLE() + "Prevision meteorologique jours par jours :\n" + getJaune());
            displayDayByDay(Ville);
            System.out.println("---------------------------------------------" + getBleu());

        } catch (Exception e) {
            System.out.println("Ville inexistante veuillez Retapez un nom de ville valide");
            CliTerminal();
        }
    }


    public static void displayDayByDay(String Ville) throws IOException, ParseException {
        ReadApi.readJSONDaily(Ville);
        String filenamedaily = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + Ville.toUpperCase() + "daily.json";
        Object day1 = null, day2 = null, day3 = null, day4 = null, day5 = null, day6 = null, tempday1 = null, tempday2 = null, tempday3 = null, tempday4 = null, tempday5 = null, tempday6 = null;
        List<Object> days = Arrays.asList(day1, day2, day3, day4, day5, day6);
        List<Object> tempsdays = Arrays.asList(tempday1, tempday2, tempday3, tempday4, tempday5, tempday6);
        for (int i = 0; i < 6; i++) {
            ReadJsonArray readJsonArrayday1 = new ReadJsonArray(filenamedaily, i);
            days.set(i, AppMeteoController.getNextDay(Calendar.getInstance().getTime(), i + 1).toUpperCase());
            tempsdays.set(i, Math.round(Float.parseFloat((String) readJsonArrayday1.getTemp())) + " ºC");
            System.out.println(days.get(i) + ":" + tempsdays.get(i));
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(getRouge() + "--------------- Welcome to the weather app ----------------" + getPURPLE());
        CliTerminal();

    }
}

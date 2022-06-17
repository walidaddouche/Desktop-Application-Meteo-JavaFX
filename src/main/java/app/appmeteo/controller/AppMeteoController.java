package app.appmeteo.controller;

import app.appmeteo.JsonReader.ReadJsonArray;
import app.appmeteo.JsonReader.ReadJsonList;
import app.appmeteo.JsonReader.ReadJsonObject;
import API.ReadApi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@SuppressWarnings("All") // ©Enlever ce code quand vous modifier le projet !
public class AppMeteoController implements Initializable {

    @FXML
    public TextField jtxtFieldOfCity;
    @FXML
    public DatePicker PickerDate;
    @FXML
    public Label labelOfCity;
    @FXML
    public Label labelOfWeather;
    @FXML
    public Button searchButton;
    @FXML
    public Label labelOfTemperature;
    @FXML
    public Label labelOfTemperatureMaxAndMin;
    @FXML
    public Label labelOfUv;
    @FXML
    public Label labelOfHumidity;
    @FXML
    public Label labelOfPression;
    @FXML
    public Label labelOfWind;
    @FXML
    public ImageView weatherImage;
    public Button Style;
    public ChoiceBox listeOfFavorite;
    public AnchorPane AnchorePane;
    public Button Findfavorite;
    @FXML
    public Label labelOfWeeksDay1;
    @FXML
    public Label labelOfWeeksDay2;
    @FXML
    public Label labelOfWeeksDay3;
    @FXML
    public Label labelOfWeeksDay4;
    @FXML
    public Label labelOfWeeksDay5;
    @FXML
    public Label labelOfWeeksDay6;
    @FXML
    public Label labelTemperatureOfDay6;
    @FXML
    public Label labelTemperatureOfDay5;
    @FXML
    public Label labelTemperatureOfDay4;
    @FXML
    public Label labelTemperatureOfDay3;
    @FXML
    public Label labelTemperatureOfDay2;
    @FXML
    public Label labelTemperatureOfDay1;
    @FXML
    public ImageView iconweatherOfDay5;
    @FXML
    public ImageView iconweatherOfDay4;
    @FXML
    public ImageView iconweatherOfDay3;
    @FXML
    public ImageView iconweatherOfDay2;
    @FXML
    public ImageView iconweatherOfDay1;
    @FXML
    public ImageView iconweatherOfDay6;
    @FXML
    public Label localTime;


    File fileContainsAllFavorites = new File("src/main/java/app/appmeteo/controller/Favoris/favoris.txt");
    File file2UpdatesAllFavorites = File.createTempFile("favoris2", ".txt");
    FileWriter writer2 = new FileWriter(file2UpdatesAllFavorites, true);
    Scanner scanner = new Scanner(fileContainsAllFavorites);


    public AppMeteoController() throws IOException, ParseException {

    }


    //    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {


    }

    public static String getCurrentDayOfWeek(Date date) {
        Locale locale = Locale.FRENCH;
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        return formatter.format(date);

    }

    public static String getNextDay(Date date, int value) {
        Date next = new Date(date.getTime() + ((long) value * 1000 * 60 * 60 * 24));
        return getCurrentDayOfWeek(next);
    }

    public void SetGraphicContent(MouseEvent mouseEvent) throws IOException, ParseException, InterruptedException {
        try {
            String entree = jtxtFieldOfCity.getText();
            String imageSource = " http://openweathermap.org/img/wn/10d@2x.png ";
            /** API **/
            /** api.openweathermap.org/data/2.5/weather?q=Paris&appid=c1550e47032f753476993a9f6ba80934 **/
            ReadApi.readJSON(entree.toUpperCase());
            ReadApi.readJSONDaily(entree.toUpperCase());
            String filename = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + entree.toUpperCase() + ".json";
            String filenamedaily = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + entree.toUpperCase() + "daily.json";

            System.out.println("uper " + entree.toUpperCase());
            displayWeatherDayByDay(filenamedaily);
            ReadJsonList readJsonList = new ReadJsonList(filename);
            ReadJsonArray readJsonArray = new ReadJsonArray(filename);
            labelOfCity.setText(entree);
            Object temp = readJsonList.getMain("temp");
            labelOfTemperature.setText((Math.round(Float.parseFloat(temp.toString()))) + " °C");
            labelOfWeather.setText((String) readJsonArray.ReadJsonListObject("description"));
            labelOfTemperatureMaxAndMin.setText(readJsonList.getMain("temp_min") + " °C" + " - " + readJsonList.getMain("temp_max") + " °C");
            labelOfWind.setText(readJsonList.getWind("speed") + " Km/h");
            labelOfPression.setText(readJsonList.getMain("pressure") + " Pa");
            labelOfHumidity.setText(readJsonList.getMain("humidity") + " %");
            // labelOfUv.setText(" non disponible ");
            String icon = (String) readJsonArray.ReadJsonListObject("icon");
            String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";
            weatherImage.setImage(new Image(iconUrl));
            System.out.println(labelOfWeather.toString().substring(43));
            if (labelOfWeather.toString().substring(43).length() >= 21) labelOfWeather.setLayoutX(5);
            else labelOfWeather.setLayoutX(60);
        } catch (FileNotFoundException e) {
            jtxtFieldOfCity.setText("ERROR!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ville introuvable");
            alert.setHeaderText(null);
            alert.setContentText("Message d'erreur : Veuillez saisir une ville correctement. ");
            alert.showAndWait();
        }

    }

    public void add_favorite(MouseEvent keyEvent) throws IOException {
        FileWriter fileWriter = new FileWriter(fileContainsAllFavorites, true);
        if (!listeOfFavorite.getItems().contains(jtxtFieldOfCity.getText().toUpperCase()) && !jtxtFieldOfCity.getText().isEmpty()) {
            listeOfFavorite.getItems().add(jtxtFieldOfCity.getText().toUpperCase());
            fileWriter.write(jtxtFieldOfCity.getText().toUpperCase() + "\n");
        }
        fileWriter.close();
    }

    @SuppressWarnings("All")
    public void delete_favoris(MouseEvent mouseEvent) throws IOException {
        String Deleted_String = listeOfFavorite.getSelectionModel().getSelectedItem().toString();
        listeOfFavorite.getItems().remove(Deleted_String);
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("src/main/java/app/appmeteo/controller/Favoris/favoris.txt"), StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(Deleted_String)) {
                fileContent.remove(Deleted_String);
                break;
            }
        }
        Files.write(Path.of("src/main/java/app/appmeteo/controller/Favoris/favoris.txt"), fileContent, StandardCharsets.UTF_8);
    }

    @SuppressWarnings("All")
    public void FavoriteFinder(MouseEvent mouseEvent) throws IOException, ParseException {
        String String_Value = listeOfFavorite.getSelectionModel().getSelectedItem().toString();
        ReadApi.readJSON(String_Value.toUpperCase());
        ReadApi.readJSONDaily(String_Value.toUpperCase());
        String filenameCurrentDay = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + String_Value.toUpperCase() + ".json";
        String filenamedaily = "src/main/java/app/appmeteo/JsonReader/exemple JSON/" + String_Value.toUpperCase() + "daily.json";
        System.out.println("uper " + String_Value.toUpperCase());
        ReadJsonList readJsonList = new ReadJsonList(filenameCurrentDay);
        ReadJsonObject readJsonObject = new ReadJsonObject(filenameCurrentDay);
        ReadJsonArray readJsonArray = new ReadJsonArray(filenameCurrentDay);
        labelOfCity.setText(String_Value);
        Object temp = readJsonList.getMain("temp");
        labelOfTemperature.setText((Math.round(Float.parseFloat(temp.toString()))) + " °C");
        labelOfWeather.setText((String) readJsonArray.ReadJsonListObject("description"));
        labelOfTemperatureMaxAndMin.setText(readJsonList.getMain("temp_min") + " °C" + " - " + readJsonList.getMain("temp_max") + " °C");
        labelOfWind.setText(readJsonList.getWind("speed") + " Km/h");
        labelOfPression.setText(readJsonList.getMain("pressure") + " Pa");
        labelOfHumidity.setText(readJsonList.getMain("humidity") + " %");
        String icon = (String) readJsonArray.ReadJsonListObject("icon");
        String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";
        weatherImage.setImage(new Image(iconUrl));
        if (labelOfWeather.toString().substring(43).length() >= 21) labelOfWeather.setLayoutX(5);
        else labelOfWeather.setLayoutX(60);
        displayWeatherDayByDay(filenamedaily);

    }

    @SuppressWarnings("All")
    public void updateFavoris(MouseEvent mouseEvent) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileContainsAllFavorites));
        if (listeOfFavorite.getItems().isEmpty()) {
            while ((line = reader.readLine()) != null && !listeOfFavorite.getItems().contains(line.toUpperCase())) {
                listeOfFavorite.getItems().add(line.toUpperCase());
            }
        }
    }

    @SuppressWarnings("All")
    public void displayWeatherDayByDay(String filenamedaily) throws IOException, ParseException {
        List<Label> dayOfWeeks = Arrays.asList(labelOfWeeksDay1, labelOfWeeksDay2, labelOfWeeksDay3, labelOfWeeksDay4, labelOfWeeksDay5, labelOfWeeksDay6);
        List<Label> temperaturesAlldays = Arrays.asList(labelTemperatureOfDay1, labelTemperatureOfDay2, labelTemperatureOfDay3, labelTemperatureOfDay4, labelTemperatureOfDay5, labelTemperatureOfDay6);
        List<ImageView> imageViewList = Arrays.asList(iconweatherOfDay1, iconweatherOfDay2, iconweatherOfDay3, iconweatherOfDay4, iconweatherOfDay5, iconweatherOfDay6);
        for (int i = 0; i < 6; i++) {
            ReadJsonArray readJsonArrayday1 = new ReadJsonArray(filenamedaily, i);
            dayOfWeeks.get(i).setText(getNextDay(Calendar.getInstance().getTime(), i + 1).toUpperCase());
            temperaturesAlldays.get(i).setText(String.valueOf(Math.round(Float.parseFloat((String) readJsonArrayday1.getTemp()))) + "ºC");
            imageViewList.get(i).setImage(new Image(readJsonArrayday1.getICon()));
            //Center elements when it's displayed .
            if (dayOfWeeks.get(i).toString().substring(33).length() == 6 || dayOfWeeks.get(i).toString().substring(33).length() == 7) {
                switch (i) {
                    case 0:
                        dayOfWeeks.get(i).setLayoutX(290);
                        break;
                    case 1:
                        dayOfWeeks.get(i).setLayoutX(468);
                        break;
                    case 2:
                        dayOfWeeks.get(i).setLayoutX(660);
                        break;
                    case 3:
                        dayOfWeeks.get(i).setLayoutX(860);
                        break;
                    case 4:
                        dayOfWeeks.get(i).setLayoutX(1050);
                        break;
                    case 5:
                        dayOfWeeks.get(i).setLayoutX(1243);
                        break;
                }


            }
        }

    }

    public void changeTime(MouseEvent mouseEvent) {
        localTime.setText((String.valueOf(LocalTime.now().getHour())) + ":" + String.valueOf(LocalTime.now().getMinute()));

    }
}




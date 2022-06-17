package app.appmeteo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("All")// ©Enlever ce code quand vous modifier le projet !
public class AppMeteo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/appmeteo/view/appmeteo.fxml"));
        root.getStylesheets().addAll(String.valueOf(this.getClass().getResource("/StyleSheet/Style.css")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("AppMeteo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by valeria on 26.03.2018.
 */
public class Controller implements Initializable{

    @FXML
    private Button cinema;
    @FXML
    private Button film;
    @FXML
    private Button seance;
    @FXML
    private  Button genre;
    @FXML
    public  void showGenre()
    {

        Stage primaryStage = new Stage();
        AnchorPane rootLayout;
        primaryStage.setTitle("Таблица \"Genre\"");
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("genre.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void showCinema()
    {

        Stage primaryStage = new Stage();
        AnchorPane rootLayout;
        primaryStage.setTitle("Таблица \"Cinema\"");
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("cinema.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public  void showFilm()
    {

        Stage primaryStage = new Stage();
        AnchorPane rootLayout;
        primaryStage.setTitle("Таблица \"Film\"");
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("film.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void showSeance()
    {

        Stage primaryStage = new Stage();
        AnchorPane rootLayout;
        primaryStage.setTitle("Таблица \"Seance\"");
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("seance.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Записуємо порожній рядок замість "No content in table":
        //tableViewCensuses.setPlaceholder(new Label(""));
    }
}

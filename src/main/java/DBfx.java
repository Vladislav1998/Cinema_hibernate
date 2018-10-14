import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by valeria on 26.03.2018.
 */
public class DBfx extends Application {
    public static SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
    public  static Session session=sessionFactory.openSession();;
    public static Session getF()
    {
        return session;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("file.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Cтруктура БД \"Movie\"");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop(){

        session.close();
        sessionFactory.close();
    }
    public static void main(String[] args) {
        launch(args);
    }

}

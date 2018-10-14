import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by valeria on 27.03.2018.
 */
public class CinemaController implements Initializable{

    @FXML
    private TableView table;
    @FXML
    private TableColumn<Cinema, String> title;
    @FXML
    private TableColumn<Cinema, String> address;
    @FXML
    private TableColumn<Cinema, String> site;
    @FXML
    private Button add;
    @FXML
    private  Button delete;


    private  String cinemaTitle;
    private  String cinemaAddress;
    private String cinemaWebsite;
    private  int ID;

    private ObservableList<Cinema> obsList;
    List<Cinema> cinema;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM cinema");
        query.addEntity(Cinema.class);
        table.setEditable(true);
        cinema=query.getResultList();
        obsList= FXCollections.observableList(cinema);
        for (int i = 0; i < obsList.size(); i++) {
            table.setItems(obsList);
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            title.setCellFactory(
                    textFieldTableCell.<Cinema>forTableColumn());
            title.setOnEditCommit(t -> updateTitle(t));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            address.setCellFactory(
                    textFieldTableCell.<Cinema>forTableColumn());
            address.setOnEditCommit(t -> updateAddress(t));
            site.setCellValueFactory(new PropertyValueFactory<>("website"));
            site.setCellFactory(
                    textFieldTableCell.<Cinema>forTableColumn());
            site.setOnEditCommit(t -> updateSite(t));




        }
    }

    private void updateTitle(TableColumn.CellEditEvent<Cinema, String> t) {
        Cinema c = (Cinema) t.getTableView().getItems().get(t.getTablePosition().getRow());
        String title=c.getTitle();
        cinemaTitle=title;
        for(int i=0;i<cinema.size();i++)
        {
            if(cinema.get(i).getTitle().equals(title))
            {
                ID=cinema.get(i).getCinemaID();
                Transaction tr= DBfx.getF().beginTransaction();
                cinema.get(i).setTitle(t.getNewValue());
                tr.commit();
            }

        }

    }

    private void updateAddress(TableColumn.CellEditEvent<Cinema, String> t) {
        Cinema c = (Cinema) t.getTableView().getItems().get(t.getTablePosition().getRow());
        cinemaAddress=c.getAddress();
        for(int i=0;i<cinema.size();i++)
        {
            if(cinema.get(i).getAddress().equals(cinemaAddress))
            {
                ID=cinema.get(i).getCinemaID();
                Transaction tr= DBfx.getF().beginTransaction();
                cinema.get(i).setAddress(t.getNewValue());
                tr.commit();
            }

        }

    }

    private void updateSite(TableColumn.CellEditEvent<Cinema, String> t) {
        Cinema c = (Cinema) t.getTableView().getItems().get(t.getTablePosition().getRow());
        cinemaWebsite=c.getWebsite();
        for(int i=0;i<cinema.size();i++)
        {
            if(cinema.get(i).getWebsite().equals(cinemaWebsite))
            {
                ID=cinema.get(i).getCinemaID();
                Transaction tr= DBfx.getF().beginTransaction();
                cinema.get(i).setWebsite(t.getNewValue());
                tr.commit();
            }

        }

    }
    @FXML
    public  void doAdd()
    {   Cinema c=new Cinema("","","");
        obsList.add(c);
        DBfx.getF().save(c);
    }

    @FXML
    public  void doDelete()
    {
        for(int i=0;i<cinema.size();i++)
        {
            if(cinema.get(i).getCinemaID()==ID)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES ,ButtonType.NO);
                alert.setTitle("Таблица \"Cinema\"");
                alert.setHeaderText("Вы действительно хотите удалить кинотеатр \""+cinema.get(i).getTitle()+"\"?");
                        Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Transaction tr= DBfx.getF().beginTransaction();
                    DBfx.getF().delete(cinema.get(i));
                    tr.commit();
                    obsList.remove(i);
                }
            }
        }
    }
}

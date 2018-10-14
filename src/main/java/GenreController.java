import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Created by valeria on 26.03.2018.
 */
public class GenreController implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TableColumn<Genre, String> column;
    @FXML
    private Button add;
    @FXML
    private  Button delete;
    private  String objTitle;
    private  int ID;

    private ObservableList<Genre> obsList;

    List<Genre> genre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM genre");
        query.addEntity(Genre.class);
        table.setEditable(true);
        genre=query.getResultList();
        obsList= FXCollections.observableList(genre);
        for (int i = 0; i < obsList.size(); i++) {
            table.setItems(obsList);
            column.setCellValueFactory(new PropertyValueFactory<>("title"));
            column.setCellFactory(
                    textFieldTableCell.<Genre>forTableColumn());
             column.setOnEditCommit(t -> updateTitle(t));


        }
    }

    private void updateTitle(TableColumn.CellEditEvent<Genre, String> t) {
        Genre c = (Genre) t.getTableView().getItems().get(t.getTablePosition().getRow());
        String title=c.getTitle();
        objTitle=title;
        for(int i=0;i<genre.size();i++)
        {
            if(genre.get(i).getTitle().equals(title))
            {
                ID=genre.get(i).getGenreID();
                Transaction tr= DBfx.getF().beginTransaction();
                genre.get(i).setTitle(t.getNewValue());
                tr.commit();
            }

        }

    }


    @FXML
    public  void doAdd()
    {   Genre g=new Genre("");
        obsList.add(g);
        DBfx.getF().save(g);
    }

    @FXML
    public  void doDelete()
    {
        for(int i=0;i<genre.size();i++)
        {
            if(genre.get(i).getGenreID()==ID)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", ButtonType.YES ,ButtonType.NO);
                alert.setTitle("Таблица \"Genre\"");
                alert.setHeaderText("Вы действительно хотите удалить жанр \""+genre.get(i).getTitle()+"\"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Transaction tr= DBfx.getF().beginTransaction();
                    DBfx.getF().delete(genre.get(i));
                    tr.commit();
                    obsList.remove(i);
                }
            }
        }
    }



}

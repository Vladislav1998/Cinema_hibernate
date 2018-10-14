import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Created by valeria on 27.03.2018.
 */
public class FilmController implements Initializable{
    @FXML
    SplitMenuButton genres;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<FilmView, String> title;
    @FXML
    private TableColumn<FilmView, String> description;
    @FXML
    private TableColumn<FilmView, String> countrie;
    @FXML
    private TableColumn<FilmView, Integer> year;
    @FXML
    private TableColumn<FilmView, Integer> duration;
    @FXML
    private TableColumn<FilmView, Integer> genreID;
    @FXML
    private Button add;
    @FXML
    private  Button delete;
    private List<FilmView> filmViev=new ArrayList<>();
    private List<Film>film;
    private  String filmTitle;
    private  String filmDescr;
    private String filmCount;
    private int filmYear;
    private  int filmDurat;
    private int filmGID;
    private  int ID;
    private ObservableList<FilmView> obsListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM film");
        query.addEntity(Film.class);
        table.setEditable(true);
        film=query.getResultList();
        for(int i=0;i<film.size();i++)
        {
            FilmView f=new FilmView(film.get(i).getTitle(),film.get(i).getDescription(),film.get(i).getCountrie(),film.get(i).getDuration(),film.get(i).getYear(),film.get(i).getGenre().getGenreID());
            filmViev.add(f);
        }
        obsListView= FXCollections.observableList(filmViev);
        for (int i = 0; i < obsListView.size(); i++) {
            table.setItems(obsListView);
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            title.setCellFactory(
                    textFieldTableCell.<FilmView>forTableColumn());
            title.setOnEditCommit(t -> updateTitle(t));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            description.setCellFactory(
                    textFieldTableCell.<FilmView>forTableColumn());
            description.setOnEditCommit(t -> updateDescription(t));
            countrie.setCellValueFactory(new PropertyValueFactory<>("countrie"));
            countrie.setCellFactory(
                    textFieldTableCell.<FilmView>forTableColumn());
            countrie.setOnEditCommit(t -> updateCountrie(t));
            year.setCellValueFactory(new PropertyValueFactory<>("year"));
            year.setCellFactory(
                    textFieldTableCell.<FilmView,Integer>forTableColumn(new IntegerStringConverter()));
            year.setOnEditCommit(t -> updateYear(t));
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            duration.setCellFactory(
                    textFieldTableCell.<FilmView,Integer>forTableColumn(new IntegerStringConverter()));
            duration.setOnEditCommit(t -> updateDuration(t));
            genreID.setCellValueFactory(new PropertyValueFactory<>("genreID"));
            genreID.setCellFactory(
                    textFieldTableCell.<FilmView,Integer>forTableColumn(new IntegerStringConverter()));
            genreID.setOnEditCommit(t -> updateGenreId(t));
        }
        SQLQuery query2= DBfx.getF().createSQLQuery("SELECT * FROM genre");
        query2.addEntity(Genre.class);
        List<Genre> genre=query2.getResultList();
        for(int i=0;i<genre.size();i++) {
            genres.getItems().addAll(new MenuItem(genre.get(i).getGenreID()+" - "+genre.get(i).getTitle()));
        }


    }
    private void updateTitle(TableColumn.CellEditEvent<FilmView, String> t) {
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmTitle=c.getTitle();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getTitle().equals(filmTitle))
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                film.get(i).setTitle(t.getNewValue());
                tr.commit();
            }

        }
        c.setTitle(t.getNewValue());

    }
    private void updateDescription(TableColumn.CellEditEvent<FilmView, String> t) {
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmDescr=c.getDescription();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getDescription().equals(filmDescr))
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                film.get(i).setDescription(t.getNewValue());
                tr.commit();
            }

        }
        c.setDescription(t.getNewValue());
    }

    private void updateCountrie(TableColumn.CellEditEvent<FilmView, String> t) {
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmCount=c.getCountrie();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getCountrie().equals(filmCount))
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                film.get(i).setCountrie(t.getNewValue());
                tr.commit();
            }

        }
        c.setCountrie(t.getNewValue());
    }

    private void updateYear(TableColumn.CellEditEvent<FilmView, Integer> t) {
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmYear=c.getYear();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getYear()==(filmYear))
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                film.get(i).setYear(t.getNewValue());
                tr.commit();
            }

        }
        c.setYear(t.getNewValue());
    }

    private void updateDuration(TableColumn.CellEditEvent<FilmView, Integer> t) {
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmDurat=c.getDuration();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getDuration()==filmDurat)
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                film.get(i).setDuration(t.getNewValue());
                tr.commit();
            }

        }
        c.setDuration(t.getNewValue());

    }
    private void updateGenreId(TableColumn.CellEditEvent<FilmView, Integer> t) {
        List<Genre> genre;
        FilmView c = (FilmView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        filmTitle=c.getTitle();
        filmGID=t.getNewValue();
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getTitle().equals(filmTitle))
            {
                ID=film.get(i).getFilmID();
                Transaction tr= DBfx.getF().beginTransaction();
                SQLQuery query= DBfx.getF().createSQLQuery("UPDATE film SET genreID="+filmGID+" WHERE filmID="+ID);
                query.addEntity(Film.class);
                query.executeUpdate();
                tr.commit();
            }

        }
        c.setGenreID(t.getNewValue());

    }
    @FXML
    public  void doAdd()
    {
        FilmView f=new FilmView("","","",0,0,0);
        obsListView.add(f);
        Film newFilm=new Film(f.getTitle(),f.getDescription(),f.getCountrie(),f.getDuration(),f.getYear(),film.get(0).getGenre());
        Transaction tr= DBfx.getF().beginTransaction();
        DBfx.getF().save(newFilm);
        tr.commit();
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM film");
        query.addEntity(Film.class);
        film=query.getResultList();
    }

    @FXML
    public  void doDelete()
    {
        for(int i=0;i<film.size();i++)
        {
            if(film.get(i).getFilmID()==ID)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES ,ButtonType.NO);
                alert.setTitle("Таблица \"Film\"");
                alert.setHeaderText("Вы действительно хотите удалить фильм \""+film.get(i).getTitle()+"\"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Transaction tr= DBfx.getF().beginTransaction();
                    DBfx.getF().delete(film.get(i));
                    tr.commit();
                    obsListView.remove(i);
                }
            }
        }
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM film");
        query.addEntity(Film.class);
        film=query.getResultList();
    }


}

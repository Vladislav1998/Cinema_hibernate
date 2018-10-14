import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * Created by valeria on 29.03.2018.
 */
public class SeanceController implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TableColumn<SeanceView, Integer> filmID;
    @FXML
    private TableColumn<SeanceView, Integer> cinemaID;
    @FXML
    private TableColumn<SeanceView, String> date;
    @FXML
    private TableColumn<SeanceView, String> time;
    @FXML
    private TableColumn<SeanceView, Integer> seats;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private SplitMenuButton films;
    @FXML
    private SplitMenuButton cinemas;

    private List<SeanceView> seanceView=new ArrayList<>();
    private List<Seance>seance;
    private String seanceDate;
    private Time seanseTime;
    private  int senceSeats;
    private  int ID;
    private ObservableList<SeanceView> obsList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM seance");
        query.addEntity(Seance.class);
        table.setEditable(true);
        seance=query.getResultList();
        for(int i=0;i<seance.size();i++)
        {
            SeanceView s=new SeanceView(seance.get(i).getSeanceID(),seance.get(i).getFilm().getFilmID(),seance.get(i).getCinema().getCinemaID(),seance.get(i).getDate().toString(),seance.get(i).getTime().toString(),seance.get(i).getNumber_of_seats());
            seanceView.add(s);
        }
        obsList= FXCollections.observableList(seanceView);
        for (int i = 0; i < obsList.size(); i++) {
                table.setItems(obsList);
                filmID.setCellValueFactory(new PropertyValueFactory<>("filmID"));
                filmID.setCellFactory(
                        textFieldTableCell.<SeanceView, Integer>forTableColumn(new IntegerStringConverter()));
                    filmID.setOnEditCommit(t -> updateFilmID(t));
                cinemaID.setCellValueFactory(new PropertyValueFactory<>("cinemaID"));
                cinemaID.setCellFactory(
                        textFieldTableCell.<SeanceView, Integer>forTableColumn(new IntegerStringConverter()));
                cinemaID.setOnEditCommit(t -> updateCinemaID(t));
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                date.setCellFactory(
                        textFieldTableCell.<SeanceView>forTableColumn());
                date.setOnEditCommit(t -> updateDate(t));
                time.setCellValueFactory(new PropertyValueFactory<>("time"));
                time.setCellFactory(
                        textFieldTableCell.<SeanceView>forTableColumn());
                time.setOnEditCommit(t -> updateTime(t));
                seats.setCellValueFactory(new PropertyValueFactory<>("seats"));
                seats.setCellFactory(
                        textFieldTableCell.<SeanceView, Integer>forTableColumn(new IntegerStringConverter()));
                seats.setOnEditCommit(t -> updateSeats(t));
            //transacion
        }
        SQLQuery query2= DBfx.getF().createSQLQuery("SELECT * FROM film");
        query2.addEntity(Film.class);
        List<Film> f=query2.getResultList();
        for(int i=0;i<f.size();i++) {
            films.getItems().addAll(new MenuItem(f.get(i).getFilmID()+" - "+f.get(i).getTitle()));
        }
        SQLQuery query3= DBfx.getF().createSQLQuery("SELECT * FROM cinema");
        query3.addEntity(Cinema.class);
        List<Cinema> c=query3.getResultList();
        for(int i=0;i<c.size();i++) {
            cinemas.getItems().addAll(new MenuItem(c.get(i).getCinemaID()+" - "+c.get(i).getTitle()));
        }



    }

    private void updateFilmID(TableColumn.CellEditEvent<SeanceView, Integer> t) {
        SeanceView c = (SeanceView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        ID=c.getSeanceID();
        System.out.println(ID);
        if(t.getNewValue() instanceof Integer){
        int newID=t.getNewValue();
        for(int i=0;i<seance.size();i++)
        {
            if(seance.get(i).getSeanceID()==ID)
            {
                Transaction tr= DBfx.getF().beginTransaction();
                SQLQuery query= DBfx.getF().createSQLQuery("UPDATE seance SET filmID="+newID+" WHERE seanceID="+ID);
                query.addEntity(Seance.class);
                query.executeUpdate();
                tr.commit();
                tr.getStatus();
            }

        }
        c.setFilmID(t.getNewValue());}
        else
            throw  new NumberFormatException();

    }
    private void updateCinemaID(TableColumn.CellEditEvent<SeanceView, Integer> t)
    {
            SeanceView c = (SeanceView) t.getTableView().getItems().get(t.getTablePosition().getRow());
            ID = c.getSeanceID();
            int newID = t.getNewValue();
            for (int i = 0; i < seance.size(); i++) {
                if (seance.get(i).getSeanceID() == ID) {
                    Transaction tr = DBfx.getF().beginTransaction();
                    SQLQuery query = DBfx.getF().createSQLQuery("UPDATE seance SET cinemaID=" + newID + " WHERE seanceID=" + ID);
                    query.addEntity(Seance.class);
                    query.executeUpdate();
                    tr.commit();
                }

            }
            c.setFilmID(t.getNewValue());
    }

    private void updateDate(TableColumn.CellEditEvent<SeanceView, String> t) {
        SeanceView c = (SeanceView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        ID=c.getSeanceID();
        String newDate=t.getNewValue();
        for(int i=0;i<seance.size();i++)
        {
            if(seance.get(i).getSeanceID()==ID)
            {
                Transaction tr= DBfx.getF().beginTransaction();
                SQLQuery query= DBfx.getF().createSQLQuery("UPDATE seance SET date=\""+newDate+"\" WHERE seanceID="+ID);
                query.addEntity(Seance.class);
                query.executeUpdate();
                tr.commit();
            }

        }
        c.setDate(t.getNewValue());


    }
    private void updateTime(TableColumn.CellEditEvent<SeanceView, String> t) {
        SeanceView c = (SeanceView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        ID=c.getSeanceID();
        String newTime=t.getNewValue();
        for(int i=0;i<seance.size();i++)
        {
            if(seance.get(i).getSeanceID()==ID)
            {
                Transaction tr= DBfx.getF().beginTransaction();
                SQLQuery query= DBfx.getF().createSQLQuery("UPDATE seance SET time=\""+newTime+"\" WHERE seanceID="+ID);
                query.addEntity(Seance.class);
                query.executeUpdate();
                tr.commit();
            }

        }
        c.setTime(t.getNewValue());

    }

    private void updateSeats(TableColumn.CellEditEvent<SeanceView, Integer> t) {
        SeanceView c = (SeanceView) t.getTableView().getItems().get(t.getTablePosition().getRow());
        ID=c.getSeanceID();
        //Integer newSeats=t.getNewValue();
        for(int i=0;i<seance.size();i++)
        {
            if(seance.get(i).getSeanceID()==ID)
            {
                Transaction tr= DBfx.getF().beginTransaction();
                seance.get(i).setNumber_of_seats(t.getNewValue());
                tr.commit();
            }

        }
        c.setSeats(t.getNewValue());
    }

    @FXML
    public  void doAdd()
    {
        Transaction tr= DBfx.getF().beginTransaction();
        Seance newSeance=new Seance();
        newSeance.setFilm(seance.get(0).getFilm());
        newSeance.setCinema(seance.get(0).getCinema());
        newSeance.setDate(new Date(0,0,0));
        newSeance.setTime(new Time(0,0,0));
        newSeance.setNumber_of_seats(0);
        DBfx.getF().save(newSeance);
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM seance");
        query.addEntity(Seance.class);
        seance=query.getResultList();
        tr.commit();
        SeanceView f=new SeanceView(newSeance.getSeanceID(),newSeance.getFilm().getFilmID(),newSeance.getCinema().getCinemaID(),newSeance.getDate().toString(),newSeance.getTime().toString(),0);
        obsList.add(f);
    }

   @FXML
    public  void doDelete()
    {
        for(int i=0;i<seance.size();i++)
        {
            if(seance.get(i).getSeanceID()==ID)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", ButtonType.YES ,ButtonType.NO);
                alert.setTitle("Таблица \"\"");
                alert.setHeaderText("Вы действительно хотите удалить сеанс с датой \""+seance.get(i).getDate()+"\", временем \""+seance.get(i).getTime()+" и количеством мест="+seance.get(i).getNumber_of_seats()+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Transaction tr= DBfx.getF().beginTransaction();
                    DBfx.getF().delete(seance.get(i));
                    tr.commit();
                    obsList.remove(i);
                }

            }


        }
        SQLQuery query= DBfx.getF().createSQLQuery("SELECT * FROM seance");
        query.addEntity(Seance.class);
        seance=query.getResultList();
    }

}

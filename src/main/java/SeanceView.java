import java.sql.Time;
import java.sql.Date;


public class SeanceView {
    private  int seanceID;
    private  int filmID;
    private int cinemaID;
    private String date;
    private String time;
    private int seats;

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSeanceID() {
        return seanceID;
    }

    public void setSeanceID(int seanceID) {
        this.seanceID = seanceID;
    }

    public SeanceView(int seanceID, int filmID, int cinemaID, String date, String time, int seats) {
        this.seanceID = seanceID;
        this.filmID = filmID;
        this.cinemaID = cinemaID;
        this.date = date;
        this.time = time;
        this.seats = seats;
    }


    public  SeanceView(){};


}

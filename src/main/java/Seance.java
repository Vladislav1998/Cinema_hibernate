import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

/**
 * Created by valeria on 26.03.2018.
 */

@Entity
@Table(name="seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int seanceID;

    @Column
    private Date date;
    @Column
    private Time time;
    @Column
    private int number_of_seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filmID")
    private  Film film;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinemaID")
    private Cinema cinema;
    public Seance() {
    }

    public Seance(Film film, Cinema cinema,Date date, Time time, int number_of_seats ) {
        this.date = date;
        this.time = time;
        this.number_of_seats = number_of_seats;
        this.film = film;
        this.cinema = cinema;
    }

    public int getSeanceID() {

        return seanceID;
    }

    public void setSeanceID(int seanceID) {
        this.seanceID = seanceID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }


}

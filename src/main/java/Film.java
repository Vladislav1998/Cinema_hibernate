import javax.persistence.*;
import java.util.Set;

/**
 * Created by valeria on 26.03.2018.
 */

@Entity
@Table(name="film")
public class Film {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int filmID;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private  int duration;
    @Column
    private  String countrie;
    @Column
    private  int year;

    public Film(String title, String description,  String countrie,int duration, int year) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.countrie = countrie;
        this.year = year;
    }

    public Film(String title, String description,String countrie, int year,int duration,  Genre genre) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.countrie = countrie;
        this.year = year;
        this.genre = genre;
    }

    public Film()
    {

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreID")
    private  Genre genre;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "film")
    private Set<Seance> seances;
    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountrie() {
        return countrie;
    }

    public void setCountrie(String countrie) {
        this.countrie = countrie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }


}

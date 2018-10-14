import javax.persistence.*;
import java.util.Set;

/**
 * Created by valeria on 26.03.2018.
 */

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int genreID;

    @Column(name = "title")
    private  String title;

    public Genre(String title) {
        this.title = title;
    }

    public Genre() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private Set<Film> films;

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }



    public int getGenreID() {

        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Genre{" +
                "genreID=" + genreID +
                ", title='" + title +
                '}';
    }
}

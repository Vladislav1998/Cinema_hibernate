import javax.persistence.*;
import java.util.Set;

/**
 * Created by valeria on 26.03.2018.
 */
@Entity
@Table(name="cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int cinemaID;

    @Column(name="title")
    private  String title;

    @Column(name = "address")
    private  String address;

    @Column(name = "website")
    private  String website;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cinema")
    private Set<Seance> seances;

    public Cinema(String title, String address, String website) {
        this.title = title;
        this.address = address;
        this.website = website;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaID=" + cinemaID +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public Cinema() {
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }
}

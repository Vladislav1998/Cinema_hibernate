/**
 * Created by valeria on 28.03.2018.
 */
public class FilmView {
    private String title;
    private String description;
    private  String countrie;
    private  int duration;
    private  int year;
    private  int genreID;

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

    public String getCountrie() {
        return countrie;
    }

    public void setCountrie(String countrie) {
        this.countrie = countrie;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genrwID) {
        this.genreID = genrwID;
    }

    public FilmView(String title, String description, String countrie, int duration, int year, int genrwID) {
        this.title = title;
        this.description = description;
        this.countrie = countrie;
        this.duration = duration;
        this.year = year;
        this.genreID = genrwID;
    }

    public FilmView() {
    }
}

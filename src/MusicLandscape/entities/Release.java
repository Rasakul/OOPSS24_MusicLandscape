package MusicLandscape.entities;

/**
 * This class represents a release of a specific artist, like an album or a music video.
 */
public abstract class Release {

    /**
     * The artist that released this release. A null artist represents an unknown artist. By default it is the default
     * artist.
     */
    private Artist artist = new Artist();
    /**
     * The title of this release. A null title represents an unknown title.
     */
    private String title;
    /**
     * The year in which this release was released. The year cannot be negative, value 0 (zero) representing unknown
     * year. By default the year is 1900.
     */
    private int year = 1900;


    /**
     * Creates a default release. A default release has default values for all fields (see there).
     */
    public Release() {

    }

    /**
     * Creates a copy of a release. This release is a deep copy of an existing release - the original.
     */
    public Release(Release orig) {
        this.title = orig.title;
        this.artist = new Artist(orig.artist);
        this.year = orig.year;
    }

    /**
     * Create a release with a specific title of a specific artist in a specific year.
     */
    public Release(String title, Artist artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }


    /**
     * Get the artist of this release.
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Set the artist of this release. This method accepts null arguments.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Get the title of this release. If the title is unknown a null String is returned.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of this release. This method accepts null Strings.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the year in which this release was released.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of this release. If the argument is outside the allowed range it is ignored.
     */
    public void setYear(int year) {
        if (year >= 0) this.year = year;
    }

    /**
     * Get a textual representation of this release. The string representation of a release is
     * "title-artist-year-totaltime" (without quotes and all names substituted with their respective values) unknown
     * field are represented by the string "unknown" (without quotes)
     **/
    @Override
    public java.lang.String toString() {
        String titleToString;
        if (title == null) {
            titleToString = "unknown";
        } else {
            titleToString = title;
        }
        String yearToString;
        if (year == 0) {
            yearToString = "unknown";
        } else {
            yearToString = String.valueOf(year);
        }
        return titleToString + "-" + artist.toString() + "-" + yearToString + "-" + totalTime();
    }

    /**
     * Get the total time of this release. The implementation of this method is left to concrete subclasses.
     */
    public abstract int totalTime();

}

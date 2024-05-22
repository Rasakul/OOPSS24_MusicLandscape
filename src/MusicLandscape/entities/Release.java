package MusicLandscape.entities;

public abstract class Release {

    /**
     * The artist that released this release.
     * A null artist represents an unknown artist.
     */
    private Artist artist;
    /**
     * The title of this release.
     * A null title represents an unknown title.
     */
    private String title;
    /**
     * The year in which this release was released.
     * The year cannot be negative, value 0 (zero) representing unknown year.
     */
    private int year;


    /**
     * Creates a default release.
     * A default release has default values for all fields (see there).
     */
    public Release() {

    }

    /**
     * Creates a copy of a release.
     * This release is a deep copy of an existing release - the original.
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
    Artist getArtist() {
        return artist;
    }

    /**
     * Get the title of this release.
     * If the title is unknown a null String is returned.
     */
    String getTitle() {
        return title;
    }

    /**
     * Get the year in which this release was released.
     */
    int getYear() {
        return year;
    }

    /**
     * Set the artist of this release.
     * This method accepts null arguments.
     */
    void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Set the title of this release.
     * This method accepts null Strings.
     */
    void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the year of this release.
     * If the argument is outside the allowed range it is ignored.
     */
    void setYear(int year) {
        this.year = year;
    }

    /**
     * Get a textual representation of this release.
     * The string representation of a release is "title-artist-year-totaltime" (without quotes and all names
     * substituted with their respective values) unknown field are represented by the string "unknown" (without quotes)
     **/
    @Override
    public java.lang.String toString() {
        return "title-artist-year-totaltime";
    }

    /**
     * Get the total time of this release.
     * The implementation of this method is left to concrete subclasses.
     */
    abstract int totalTime();

}

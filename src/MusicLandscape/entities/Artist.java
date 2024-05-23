package MusicLandscape.entities;

/**
 * This class represents an artist of performing arts, like a band.
 */
public class Artist {

    /**
     * holds the name of the artist initial value should be unchanged
     */
    private String name = "unknown";

    /**
     * creates a copy of an artist
     */
    public Artist(Artist toCopy) {
        this.name = toCopy.name;
    }

    /**
     * creates n artist with a certain name
     */
    public Artist(String name) {
        this.name = name;
    }

    /**
     * creates a default artist a default artists name is the String "unknown" (without quotes)
     */
    public Artist() {
    }

    /**
     * gets the name of this artist
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this artist. the name of an artist cannot be null or empty. if an invalid argument is passed to
     * the method the state of the object remains unchanged
     */
    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            this.name = "unknown";
        }
    }

    /**
     * returns a String representation of this Artist This should be either the name of the Artist, or "unknown" if the
     * name is not available
     */
    @Override
    public String toString() {
        return getName();
    }
}

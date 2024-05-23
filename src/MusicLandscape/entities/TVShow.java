package MusicLandscape.entities;

/**
 * represents the appearance of an artist in (a certain edition of) a TVShow as a specific event. e.g. "Johnny Cash" on
 * "The Tonight Show"
 */
public class TVShow extends Event {
    /**
     * the name of this TV show The name of a TVShow cannot be empty or composed of whitespace only. A null name
     * represents an unknown name.
     */
    private String name;
    /**
     * the number of viewers of this TVShow must be non-negative 0 means unknown
     */
    private int viewers = 0;

    /**
     * creates a deep copy of a TVShow
     */
    public TVShow(TVShow tvs) {
        super(tvs);
        this.name = tvs.name;
        this.viewers = tvs.viewers;
    }

    /**
     * creates a TV show from an event this constructor performs some kind of promotion such that it takes a generic
     * event and creates a TV show which is a (deep) copy of the original event. The resulting TV show has unknown name
     * and unknown viewers.
     */
    public TVShow(Event e) {
        super(e);
    }

    /**
     * creates a default TVShow a default TVShow is a default event with an unknown name and an unknown number of
     * viewers.
     */
    public TVShow() {
    }

    /**
     * gets the name of this TVShow
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this TVShow illegal arguments are ignored
     */
    public void setName(String name) {
        if (name != null && !name.isBlank())
            this.name = name;
    }

    /**
     * gets the number of viewers of this TVShow
     */
    public int getViewers() {
        return viewers;
    }

    /**
     * sets the viewers of this TVshow illegal arguments are ignored
     */
    public void setViewers(int viewers) {
        if (viewers >= 0)
            this.viewers = viewers;
    }

    /**
     * returns the impact of this event the impact is an estimation of the number of people who took notice of this
     * event for a TV show event, the impact is the audience times 2. audience are all viewers and attendees of a show
     */
    @Override
    public int impact() {
        return getAudience() * 2;
    }

    private int getAudience() {
        return (getViewers() + getAttendees());
    }

    /**
     * returns a String representation of this TV show the string representation of a TV show is (without quotes):
     * <p>
     * "artist name" @ "show name" on "date"
     * <p>
     * "description"
     * <p>
     * ("audience" attending ("impact"))
     * <p>
     * audience are all viewers and attendees of a show
     */
    @Override
    public String toString() {
        return getArtist().toString()
                + " @ " + getName()
                + " on " + getDate()
                + "\n" + getDescription()
                + "\n" + "(" + getAudience() + " attending (" + impact() + "))";
    }
}

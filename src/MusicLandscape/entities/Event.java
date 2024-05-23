package MusicLandscape.entities;

import MusicLandscape.Date;
import MusicLandscape.Venue;

/**
 * represents a generic event of an artist in a certain venue on certain date
 */
public class Event {

    /**
     * the artist who appeared at this event the artist of an event cannot be null
     */
    private Artist artist = new Artist();
    /**
     * the number of attendees of this event. the number of attendees for an event is a non-negative number (>=0)
     */
    private int attendees;
    /**
     * the date on which this event takes place a null date represents an unknown date
     */
    private Date date;
    /**
     * description of this event default description is an empty String like
     */
    private String description = "";
    /**
     * the venue at which this event takes place a null venue represents an unknown venue
     */
    private Venue venue;

    /**
     * creates a default event a default event has a default artist and an empty description. all other information is
     * unknown (See docu for fields)
     */
    public Event() {
    }

    /**
     * creates a deep copy of an event
     */
    public Event(Event event) {
        this.artist = new Artist(event.artist);
        this.attendees = event.attendees;
        this.date = new Date(event.date);
        this.description = event.description;
        this.venue = new Venue(event.venue);
    }

    /**
     * gets the artist of this event
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * sets the artist of this event the artist of an event cannot be null
     */
    public void setArtist(Artist artist) {
        if (artist != null) this.artist = artist;
    }

    /**
     * gets the number of attendees of this event
     */
    public int getAttendees() {
        return attendees;
    }

    /**
     * sets the number of attendess of this event the number of attendees must be a non-negative number. When called
     * with invalid arguments this event remains unchaged.
     */
    public void setAttendees(int attendees) {
        if (attendees >= 0) this.attendees = attendees;
    }

    /**
     * gets the date of this event this method is defensive in the sense that it returns a copy of the date
     */
    public Date getDate() {
        if (date != null) {
            return new Date(date);
        } else {
            return null;
        }
    }

    /**
     * sets the date of this event an unknown date is represented by a null date. this method is defensive in the sense
     * that this event keep a copy of the original date
     */
    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date);
        } else {
            this.date = null;
        }
    }

    /**
     * gets the description of this event
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of this event description can not be null
     */
    public void setDescription(String description) {
        if (description != null) this.description = description;
        else this.description = "";
    }

    /**
     * gets the venue of this event
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * sets the venue of this event
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * returns the impact of this event the impact is an estimation of the number of people who took notice of this
     * event for a generic event, the impact is the number of attendees times 2.
     */
    public int impact() {
        return getAttendees() * 2;
    }

    /**
     * returns a String representation of this event the string representation of an event is (without quotes, but
     * including line breaks):
     * <p>
     * "artist" @ "venue name" on "date"
     * <p>
     * "description"
     * <p>
     * ("attendees" attending ("impact"))
     * <p>
     * if a value is not available, replace it with "unknown"
     */
    @Override
    public String toString() {
        String venueName = "unknown";
        if (getVenue() != null) {
            venueName = getVenue().getName();
        }
        return getArtist().toString()
                + " @ " + venueName
                + " on " + getDate()
                + "\n" + getDescription()
                + "\n" + "(" + getAttendees() + " attending (" + impact() + "))";
    }
}

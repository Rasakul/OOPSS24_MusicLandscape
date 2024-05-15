package MusicLandscape.entities;

import MusicLandscape.Date;
import MusicLandscape.Venue;

public class Event {

    private Artist artist = new Artist();
    private int attendees;
    private Date date;
    private String description = "";
    private Venue venue;

    public Event() {
    }

    public Event(Event event) {
        this.artist = new Artist(event.artist);
        this.attendees = event.attendees;
        this.date = new Date(event.date);
        this.description = event.description;
        this.venue = new Venue(event.venue);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        if (artist != null) this.artist = artist;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        if (attendees >= 0) this.attendees = attendees;
    }

    public Date getDate() {
        if (date != null) {
            return new Date(date);
        } else {
            return null;
        }
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date);
        } else {
            this.date = null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) this.description = description;
        else this.description = "";
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int impact() {
        return getAttendees() * 2;
    }

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

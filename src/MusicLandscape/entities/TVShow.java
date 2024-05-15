package MusicLandscape.entities;

public class TVShow extends Event {
    private String name;
    private int viewers = 0;

    public TVShow(TVShow tvs) {
        super(tvs);
        this.name = tvs.name;
        this.viewers = tvs.viewers;
    }

    public TVShow(Event e) {
        super(e);
    }

    public TVShow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank())
            this.name = name;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        if (viewers >= 0)
            this.viewers = viewers;
    }

    @Override
    public int impact() {
        return getAudience() * 2;
    }

    private int getAudience() {
        return (getViewers() + getAttendees());
    }

    @Override
    public String toString() {
        return getArtist().toString()
                + " @ " + getName()
                + " on " + getDate()
                + "\n" + getDescription()
                + "\n" + "(" + getAudience() + " attending (" + impact() + "))";
    }
}

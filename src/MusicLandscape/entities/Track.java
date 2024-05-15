package MusicLandscape.entities;

public class Track {

    private int duration = 0;
    private Artist performer = new Artist();
    private String title;
    private Artist writer = new Artist();
    private int year;

    public Track(Track toCopy) {
        this.duration = toCopy.duration;
        this.performer = new Artist(toCopy.performer);
        this.title = toCopy.title;
        this.writer = new Artist(toCopy.writer);
        this.year = toCopy.year;
    }

    public Track(int duration, Artist performer, String title, Artist writer, int year) {
        this.duration = duration;
        this.performer = performer;
        this.title = title;
        this.writer = writer;
        this.year = year;
    }

    public Track() {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
        }
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        if (performer != null) this.performer = performer;
    }

    public String getTitle() {
        if (title != null) {
            return title;
        }
        return "unknown title";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getWriter() {
        return writer;
    }

    public void setWriter(Artist writer) {
        if (writer != null) this.writer = writer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 1900 && year <= 2999) this.year = year;
    }

    public boolean writerIsKnown() {
        return writer != null && writer.getName() != null;
    }

    @Override
    public String toString() {
        String newTitle;
        if (title != null && !title.isEmpty()) {
            newTitle = title.substring(0, Math.min(title.length(), 10));
        } else {
            newTitle = "unknown";
        }
        String formattedTitle = String.format("%10s", newTitle);

        String formattedWriter = formatArtist(writer);
        String formattedPerformer = formatArtist(performer);

        // Duration
        String formattedMinutes = String.format("%02d", duration / 60);
        String formattedSeconds = String.format("%02d", duration % 60);
        String formattedDuration = formattedMinutes + ":" + formattedSeconds;

        return formattedTitle + " by " + formattedWriter + " performed by " + formattedPerformer + " (" + formattedDuration + ")";

    }

    private String formatArtist(Artist artist) {
        String newWriter;
        if (artist != null
                && artist.getName() != null
                && !artist.getName().isEmpty()) {
            newWriter = artist.getName().substring(0, Math.min(artist.getName().length(), 10));
        } else {
            newWriter = "unknown";
        }
        return String.format("%10s", newWriter);
    }
}

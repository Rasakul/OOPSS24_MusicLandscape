package MusicLandscape.entities;

import MusicLandscape.util.ConsoleScanable;

import java.util.Scanner;

/**
 * represents a piece of music that has been released on some kind of media (CD, vinyl, video, ...)
 */
public class Track implements ConsoleScanable {

    /**
     * the duration of this track in seconds the duration is a non-negative number, duration 0 (zero) represents unknown
     * duration
     */
    private int duration = 0;
    /**
     * the artist who performs this track the performer cannot be null
     */
    private Artist performer = new Artist();
    /**
     * the title of this track. a null title represents an unknown title
     */
    private String title;
    /**
     * the artist who wrote this track the writer cannot be null
     */
    private Artist writer = new Artist();
    /**
     * the year in which the Track was or will be produced valid years are between 1900- 2999
     */
    private int year = 1900;

    /**
     * creates a track with a certain title the resulting track has the specified title, all other values are default
     */
    public Track(String title) {
        this.title = title;
    }

    /**
     * creates a deep copy of a Track
     */
    public Track(Track toCopy) {
        this.duration = toCopy.duration;
        this.performer = new Artist(toCopy.performer);
        this.title = toCopy.title;
        this.writer = new Artist(toCopy.writer);
        this.year = toCopy.year;
    }

    /**
     * creates a track with a certain title the resulting track has the specified title, all other values are default
     */
    public Track(int duration, Artist performer, String title, Artist writer, int year) {
        this.duration = duration;
        this.performer = performer;
        this.title = title;
        this.writer = writer;
        this.year = year;
    }

    /**
     * creates a default track a default track has the following values: unknown title duration 0 default writer and
     * performer year 1900
     */
    public Track() {
    }

    /**
     * gets the duration of this track
     */
    public int getDuration() {
        return duration;
    }

    /**
     * sets the duration a negative value is ignored, the object remains unchanged
     */
    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
        }
    }

    /**
     * returns the performer of this track
     */
    public Artist getPerformer() {
        return performer;
    }

    /**
     * sets the performer of this track null arguments are ignored
     */
    public void setPerformer(Artist performer) {
        if (performer != null) this.performer = performer;
    }

    /**
     * gets the title of this track. if the title is not known (null) "unknown title" is returned (without quotes)
     */
    public String getTitle() {
        if (title != null) {
            return title;
        }
        return "unknown title";
    }

    /**
     * sets the title of this track.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the writer of this track
     */
    public Artist getWriter() {
        return writer;
    }

    /**
     * sets the the writer of this track null arguments are ignored
     */
    public void setWriter(Artist writer) {
        if (writer != null) this.writer = writer;
    }

    /**
     * gets the production year of this track
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the production year of this track valid years are between 1900 and 2999 other values are ignored, the object
     * remains unchanged
     */
    public void setYear(int year) {
        if (year >= 1900 && year <= 2999) this.year = year;
    }

    /**
     * this getter is used to check if the writer of this Track is known. A writer is considered as known if the name is
     * not equal to null .
     */
    public boolean writerIsKnown() {
        return writer != null && writer.getName() != null;
    }

    /**
     * returns a formatted String containing all information of this track. the String representation is (without
     * quotes):
     * <p>
     * "title by writer performed by performer (min:sec)"
     * <p>
     * where
     * <p>
     * - title stands for the title (exactly 10 chars wide) if not set, return unknown - writer stands for the writer
     * name (exactly 10 chars wide, right justified) - performer stands for the performer name (exactly 10 chars wide,
     * right justified) - min is the duration's amount of full minutes (at least two digits, leading zeros) - sec is the
     * duration's remaining amount of seconds (at least two digits, leading zeros)
     */
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

    /**
     * Guides the user through a process that allows scanning/modifying of this track with a text-based user interface.
     * This method allows modification of the following fields, in the order listed:
     * <ul>
     * <li>title</li>
     * <li>duration</li>
     * </ul>
     * For each modifiable field the process is the following:
     *  <ul>
     * <li>field name and current value are displayed</li>
     * <li>new value is read and validated</li>
     * <li>if input is valid, field is set, otherwise a short message is shown and input of this field is repeated.</li>
     * </ul>
     * <p>
     * Old values can be kept for all fields by entering an empty string. The operation cannot be cancelled,
     * instead the user must keep all former values by repeatedly entering empty strings.
     */
    @Override
    public boolean scan() {
        boolean objectChanged = false;
        Scanner scanner = new Scanner(System.in);

        // scanning title
        objectChanged = scanTitle(scanner, objectChanged);
        objectChanged = scanDuration(scanner, objectChanged);

        return objectChanged;
    }

    private boolean scanDuration(Scanner scanner, boolean objectChanged) {
        //use String input instead of int so we can detect \n inputs (would throw exception with scanner.nextInt())
        String input = String.valueOf(this.duration);
        boolean fieldChanged;
        do {
            System.out.printf("current duration: %s\n", input);
            System.out.println("enter new duration (leave empty to keep):");
            input = scanner.nextLine();
            if (input.isEmpty()) { // keep old value
                fieldChanged = false;
                break;
            }

            if (Integer.parseInt(input) < 0) {
                System.out.printf("not a valid duration (%s).\n", input);
                fieldChanged = false;
                continue;
            }
            fieldChanged = true;
            break;
        } while (true);
        if (fieldChanged) {
            setDuration(Integer.parseInt(input));
        }
        return objectChanged || fieldChanged;
    }

    private boolean scanTitle(Scanner scanner, boolean objectChanged) {
        boolean fieldChanged;
        String input = this.title;
        do {
            System.out.printf("current title: %s\n", input);
            System.out.println("enter new title (leave empty to keep):");
            input = scanner.nextLine();
            if (input.isEmpty()) { // keep old value
                fieldChanged = false;
                break;
            } else {
                fieldChanged = true;
                break;
            }
        } while (true);
        if (fieldChanged) {
            setTitle(input);
        }
        return objectChanged || fieldChanged;
    }
}

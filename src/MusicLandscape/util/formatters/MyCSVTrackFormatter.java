package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;

/**
 * This class represents the concept of csv-formatting of a track.
 */
public class MyCSVTrackFormatter extends MyTrackFormatter {

    /**
     * Creates a CSV format of a track. The csv representation of a track is
     * "title","performer","writer","year","duration"; (without quotes) No new line is added at the end of the String!.
     */
    @Override
    public String format(Track t) {
        return String.format("%s,%s,%s,%s,%s;", t.getTitle(), t.getPerformer(), t.getWriter(), t.getYear(), t.getDuration());
    }
}

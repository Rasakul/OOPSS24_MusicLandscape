package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

/**
 * This class represents the concept of comparison of two track by title.
 */
public class MyTitleComparator extends MyTrackComparator {
    /**
     * Compares two tracks by title. Comparison is performed lexicographically on the titles of the two tracks. This
     * comparator does not handle null tracks.
     *
     * @param t1 the one track to compare
     * @param t2 the other track to which t1 is to be compared
     * @return a measure of the distance between t1 and t2 in the sense of the comparator
     */
    @Override
    public int compare(Track t1, Track t2) {
        return t1.getTitle().compareTo(t2.getTitle());
    }
}

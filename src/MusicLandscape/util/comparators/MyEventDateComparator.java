package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;

/**
 * a measure of the distance between t1 and t2 in the sense of the comparator
 */
public class MyEventDateComparator extends MyEventComparator {

    /**
     * Compares two events by date. This comparator handles null arguments such that two null events are equal, and any
     * null event is smaller than any non-null event.
     *
     * @param e1 - the one track to compare
     * @param e2 - the other track to which t1 is to be compared
     * @return a measure of the distance between t1 and t2 in the sense of the comparator
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1 == null && e2 == null) return 0;

        if (e1 == null) return -1;
        if (e2 == null) return 1;

        if (e1.getDate() == null && e2.getDate() == null) return 0;

        if (e1.getDate() == null) return -1;
        if (e2.getDate() == null) return 1;

        return e1.getDate().compareTo(e2.getDate());

    }
}

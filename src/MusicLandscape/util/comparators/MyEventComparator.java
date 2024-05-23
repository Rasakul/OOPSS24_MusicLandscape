package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;

/**
 * This class represents the concept of comparison of two events. It has a single abstract method that is to be
 * implemented by concrete subclasses which implement concrete modes of comparison, e.g. by attendees, by date or
 * other.
 */
public abstract class MyEventComparator {

    /**
     * Compares two events. The result of the comparison of two events is a measure of "distance" between the tow.
     * Distance is not literal (spatial) distance but more generally the difference between the two measured in some
     * unit. If the two events are equal in a certain sense their distance is zero, if the first argument is smaller
     * than the second (again, in the sense of the (concrete) comparator) their distance is negative, positive
     * otherwise. Example: compare two events by date Event 1 (e1): March 10, 1979 Event 2 (e2): April 1, 2000 Calling
     * compare of a by-date-comparator: compare(e1,e2) would return a negative number (since e1 is before than e2)
     * Comparators may choose to handle comparison of null tracks but are not forced to.
     *
     * @param e1 - the one track to compare
     * @param e2 - the other track to which t1 is to be compared
     * @return a measure of the distance between t1 and t2 in the sense of the comparator
     */
    public abstract int compare(Event e1, Event e2);

}

// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

/**
 * This class represents the concept of comparison of two tracks. It has a single abstract method that is to be
 * implemented by concrete subclasses which implement concrete modes of comparison, e.g. by title, by duration or
 * other.
 *
 * @author TeM, JS
 * @version 234
 * @Stage ES04
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public abstract class MyTrackComparator {
    /**
     * Compares two tracks.<br>
     * <p>
     * The result of the comparison of two tracks is a measure of "distance" between the tow. Distance is not literal
     * (spatial) distance but more generally the difference between the two measured in some unit.
     * <p>
     * If the two tracks are equal in a certain sense their distance is zero, if the first argument is smaller than the
     * second (again, in the sense of the (concrete) comparator) their distance is negative, positive otherwise.
     * Example: compare two tracks by duration Track 1 (t1): duration 1200 Track 2 (t2): duration 1300
     * <p>
     * Calling compare of a by-duration-comparator: compare(t1,t2) would return a negative number (since t1 is shorter
     * than t2)
     * <p>
     * <p>
     * Comparators may choose to handle comparison of null tracks but are not forced to.
     *
     * @param t1 the one track to compare
     * @param t2 the other track to which t1 is to be compared
     * @return a measure of the distance between t1 and t2 in the sense of the comparator
     */
    public abstract int compare(Track t1, Track t2);
}

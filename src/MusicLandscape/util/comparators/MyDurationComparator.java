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
 * This class represents the concept of comparison of two track by title.
 *
 * @author TeM, JS
 * @version 234
 * @Stage ES04
 * @ProgrammingProblem.Category concrete subclass
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public class MyDurationComparator extends MyTrackComparator {
    /**
     * Compares two tracks by duration.<br>
     * <p>
     * This comparator assumes non-null arguments.
     *
     * @ProgrammingProblem.Aspect concrete method implementation
     */
    @Override
    public int compare(Track t1, Track t2) {
        return t1.getDuration() - t2.getDuration();
    }

}

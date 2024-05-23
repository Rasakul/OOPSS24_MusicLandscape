// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;

/**
 * This class represents the concept of formatting a track. It has a single abstract method that is to be implemented by
 * concrete subclasses which implement concrete formats of String representations of tracks.
 *
 * @author TeM, JS
 * @version 234
 * @Stage ES04
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public abstract class MyTrackFormatter {
    /**
     * Creates a String representation for a Track.
     *
     * @param t the track to be formatted
     * @return the formatted String representing the track
     * @ProgrammingProblem.Aspect abstract method
     */
    public abstract String format(Track t);
}

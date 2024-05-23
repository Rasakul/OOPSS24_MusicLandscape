package MusicLandscape.util;

/**
 * This interface encapsulates the concept of scanning and editing an implementing class on the console (with a
 * text-based user interface). Implementing classes provide a method scan that guides the user through the process of
 * reviewing and possibly changing current values. The resulting object is in a consistent state, i.e. it is taken care
 * that no illegal values or combination of values are accepted.
 */
public interface ConsoleScanable {

    /**
     * Guides the user through a process that allows scanning/modifying an object with a text-based user interface.
     * Implementing classes may chose to provide a cancel mechanism and/or a keep-old-value mechanism. The user
     * interaction is completely left to implementing classes and must be documented there. Documentation should
     * include:
     * <ul>
     * <li>which fields and in which order they can be modified</li>
     * <li>if and how cancellation and keeping old values works</li>
     * <li>how illegal inputs are dealt with (message, repeated input of single field or all fields,...)</li>
     * </ul>
     *
     * @return whether this object was altered or not
     */
    boolean scan();
}

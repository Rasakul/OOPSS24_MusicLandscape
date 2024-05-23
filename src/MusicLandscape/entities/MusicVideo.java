package MusicLandscape.entities;

/**
 * This class represents a music video as a concrete release of a specific artist. A music video is a video presentation
 * for a single track and has the same duration as the track.
 */
public class MusicVideo extends Release {
    /**
     * The track of this music video. A null track represents an unknown track.
     */
    private Track track;

    /**
     * Gets the track of this music video.
     */
    public Track getTrack() {
        return track;
    }

    /**
     * Sets the track of this music video.
     */
    public void setTrack(Track track) {
        this.track = track;
    }

    /**
     * Gets a String representation of this music video. The String representation of a music video simply adds
     * "-(Video)" (without quotes) to the String representation of a general release.
     */
    @Override
    public String toString() {
        return super.toString() + "-(Video)";
    }

    /**
     * Gets the total running time of this music video.
     */
    @Override
    public int totalTime() {
        if (track != null) {
            return track.getDuration();
        } else {
            return 0;
        }
    }
}

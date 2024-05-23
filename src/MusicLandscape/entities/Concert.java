package MusicLandscape.entities;

/**
 * represents a concert of a certain artist with a certain set list as a specific event. As an extension of a generic
 * event a concert provides the possibility to store the setlist. The setlist is the sequence of non-null tracks played
 * at a concert. Class concert provides methods to add tracks to the (end of the) tracklist and to reset the tracklist
 * all together (empty it).v
 */
public class Concert extends Event {
    /**
     * next free index
     */
    private int nextIdx = 0;
    /**
     * array holding the tracks of the setlist
     */
    private Track[] setList = new Track[0];

    /**
     * adds a track to the set list Tracks are added to the end of the list with the first track played at the concert
     * being stored at the beginning of the list. This method returns whether the non-null track was successfully added
     * to the setlist or not. This method does not accept/ignores null tracks.
     */
    public boolean addTrack(Track track) {
        if (track != null) {
            ensureCapacity(setList.length + 1);
            setList[nextIdx] = track;
            nextIdx++;
            return true;
        }
        return false;
    }

    /**
     * calculates the total duration (in seconds) of all tracks in the setlist More specifically the method returns an
     * estimation (lower bound) since tracks with unknown duration are treated having duration 0.
     */
    public int duration() {
        int duration = 0;
        for (Track track : setList) {
            duration += track.getDuration();
        }
        return duration;
    }

    /**
     * ensures sufficient storage for a specific number of tracks in the setlist If the requested capacity can not be
     * ensured before the call, this method increases storage thereby keeping all existing entries.
     */
    public void ensureCapacity(int length) {
        if (setList.length < length) {
            Track[] newSetList = new Track[length];
            for (int i = 0; i < setList.length; i++) {
                newSetList[i] = setList[i];
            }
            this.setList = newSetList;
        }
    }

    /**
     * gets the setlist This method returns a defensive copy, meaning it returns a copy of the setlist, which contains
     * (deep) copies of the tracks in the setlist. The returned array does not contain any null entries. If the setlist
     * is empty an array of length 0 is returned.
     */
    public Track[] getSetList() {
        Track[] toReturn = new Track[setList.length];
        for (int i = 0; i < setList.length; i++) {
            toReturn[i] = new Track(setList[i]);
        }
        return toReturn;
    }

    /**
     * sets the setList This method creates a defensive copy, meaning it sets the setlist of this concert to contain
     * (deep copies of) all non-null tracks of the argument (and only those) thereby preserving the relative ordering of
     * entries. Null entries in the argument are ignored and not part of the resulting setlist. A null argument is
     * generally ignored.
     */
    public void setSetList(Track[] tracks) {
        if (tracks != null) {
            for (Track track : tracks) {
                if (track != null) addTrack(new Track(track));
            }
        }
    }

    /**
     * returns the impact of this event the impact is an estimation of the number of people who took notice of this
     * event. For a concert, the impact is calculated from the number of attendees and the length of the concert. The
     * number of attendees is mulitplied by the duration factor, which is initially 1 but increases by one for every
     * started half hour the concert lasts. E.G: 400 people attending the concert. 75 minutes duration; duration
     * factor=3 (two full half hours, plus one started half hour) impact therefore is 400*3.
     */
    @Override
    public int impact() {
        int durationFactor = 1 + (duration() / 60 / 30);
        return getAttendees() * durationFactor;
    }

    /**
     * get the length of the playlist the length of the playlist is the number of entries in the setlist.
     */
    public int nrTracks() {
        return setList.length;
    }

    /**
     * removes all tracks from the setlist
     */
    public void resetSetList() {
        for (int i = 0; i < setList.length; i++) {
            setList[i] = null;
        }
        nextIdx = 0;
    }

    /**
     * returns a String representation of this concert * the string representation of a concert appends the following
     * line to the string representation of a generic event (without quotes): "number of tracks" tracks played, total
     * duration "time". time is displayed in the format hh:mm with leading zeros
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        int durationInSec = duration();
        int durationInMin = durationInSec / 60;
        String formattedHours = String.format("%02d", durationInMin / 60);
        String formattedMinutes = String.format("%02d", durationInMin % 60);
        String formattedDuration = formattedHours + ":" + formattedMinutes;
        return baseString + "\n" + nrTracks() + " tracks played, total duration " + formattedDuration + ".";
    }
}

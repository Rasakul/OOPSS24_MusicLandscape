package MusicLandscape.entities;

public class Concert extends Event {
    private int nextIdx = 0;
    private Track[] setList = new Track[0];

    public Concert() {
        super();
    }

    public boolean addTrack(Track track) {
        if (track != null) {
            ensureCapacity(setList.length + 1);
            setList[nextIdx] = track;
            nextIdx++;
            return true;
        }
        return false;
    }

    public int duration() {
        int duration = 0;
        for (Track track : setList) {
            duration += track.getDuration();
        }
        return duration;
    }

    public void ensureCapacity(int length) {
        if (setList.length < length) {
            Track[] newSetList = new Track[length];
            for (int i = 0; i < setList.length; i++) {
                newSetList[i] = setList[i];
            }
            this.setList = newSetList;
        }
    }

    public Track[] getSetList() {
        Track[] toReturn = new Track[setList.length];
        for (int i = 0; i < setList.length; i++) {
            toReturn[i] = new Track(setList[i]);
        }
        return toReturn;
    }

    @Override
    public int impact() {
        int durationFactor = 1 + (duration() / 60 / 30);
        return getAttendees() * durationFactor;
    }

    public int nrTracks() {
        return setList.length;
    }

    public void resetSetList() {
        for (int i = 0; i < setList.length; i++) {
            setList[i] = null;
        }
        nextIdx = 0;
    }

    public void setSetList(Track[] tracks) {
        if (tracks != null) {
            for (Track track : tracks) {
                if (track != null) addTrack(new Track(track));
            }
        }
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        int durationInSec = duration();
        int durationInMin = durationInSec / 60;
        String formattedHours = String.format("%02d", durationInMin / 60);
        String formattedMinutes = String.format("%02d", durationInMin%60);
        String formattedDuration = formattedHours + ":" + formattedMinutes;
        return baseString + "\n" + nrTracks() + " tracks played, total duration " + formattedDuration + ".";
    }
}

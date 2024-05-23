package MusicLandscape.entities;

/**
 * This class represents an album as a concrete release of a specific artist. An album has a list of tracks, which, in
 * this class, is implemented as a (singly) linked lists of tracks.
 */
public class Album extends Release {

    /**
     * The tracks of this album. More specifically this is the head of the linked list of tracks of this album. A null
     * head represents an empty list.
     */
    private Album.TrackListItem trackListHead;


    /**
     * Creates a default Album. A default album is a default release with an empty track list.
     */
    public Album() {
        super();
    }

    /**
     * Creates a copy of an album. All release parts of this album are copied as described in the release copy
     * constructor. The track list of this album contains (references to) the same tracks as the original, meaning
     * tracks are not deeply copied.
     */
    public Album(Album orig) {
        super(orig);

    }

    /**
     * Create aa album with a specific title of a specific artist in a specific year.
     */
    public Album(String title, Artist artist, int year) {
        super(title, artist, year);
    }

    /**
     * Adds a track to the list of tracks. Tracks are added to the end of the list. Null tracks are not accepted. The
     * method returns whether the list was modified. true means success (track was added) false means no success (track
     * was NOT added).
     *
     * @param t - the track to add
     * @return whether the list was modified (added successfully) or not
     */
    public boolean addTrack(Track t) {
        if (t == null) return false;

        if (trackListHead == null) {
            trackListHead = new TrackListItem(t);
        } else {
            TrackListItem current = trackListHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new TrackListItem(t);
        }
        return true;
    }

    /**
     * Gets the tracks of this album. This method creates an array containing all tracks of this album preserving their
     * current order. If the album has no tracks an array of size zero is returned. The tracks in the returned array are
     * NOT (deep) copies of the tracks currently maintained by this album, meaning that the caller can modify the tracks
     * of this album, however modification of their ordering in the list is not possible from outside.
     *
     * @return the tracks of this album in order
     */
    public Track[] getTracks() {
        Track[] tracks = new Track[nrTracks()];
        TrackListItem current = trackListHead;
        for (int i = 0; i < tracks.length; i++) {
            tracks[i] = current.track;
            current = current.next;
        }
        return tracks;
    }

    /**
     * Gets the number of tracks on this album.
     */
    public int nrTracks() {
        TrackListItem current = trackListHead;
        int nr = 0;
        while (current != null) {
            nr++;
            current = current.next;
        }
        return nr;
    }

    /**
     * Removes a track from the track from the list of tracks. Removes and returns the track at position n from the list
     * of tracks. Element numbering starts at 0, such that in a list containing a single element the position of that
     * element is 0 (zero). If the requested element does not exist in the list null is returned.
     *
     * @param n - the (zero-based) position of the track to be removed.
     * @return the removed track or null
     */
    public Track removeTrack(int n) {
        if (trackListHead == null) return null;
        if (n > nrTracks()) return null;
        Track toRemove;
        if (n == 0) {//we need to change the head
            toRemove = trackListHead.track;
            trackListHead = trackListHead.next;
        } else {

            TrackListItem previous = trackListHead;
            TrackListItem current = trackListHead.next;
            for (int i = 1; i < n; i++) {
                previous = current;
                current = current.next;
            }

            toRemove = current.track;
            previous.next = current.next; //change reference to remove
        }
        return toRemove;
    }

    /**
     * Gets a String representation of this album. The String representation of an album adds the titles of all tracks
     * to the release String representation. The list of track names is enclosed by opening and closing brackets ([,]).
     * Track titles are also enclosed by opening and closing brackets.
     */
    @Override
    public String toString() {
        TrackListItem next = trackListHead;
        StringBuilder trackList = new StringBuilder();
        while (next != null) {
            trackList.append("[");
            trackList.append(next.track.getTitle());
            trackList.append("]");
            next = next.next;
        }
        return super.toString() + "\n[" + trackList + "]";
    }

    /**
     * Gets the total running time of this album. The running time is the sum of the running times of all tracks in this
     * album. The time is returned in seconds.
     */
    @Override
    public int totalTime() {
        TrackListItem next = trackListHead;
        int totalTime = 0;
        while (next != null) {
            totalTime += next.track.getDuration();
            next = next.next;
        }
        return totalTime;
    }

    /**
     * A single item of a linked list of tracks. A single list item consists of the primary data, in our case a track,
     * and a reference to its successor, which is another list item.
     */
    private static class TrackListItem {

        /**
         * The primary data of this list item.
         */
        private final Track track;
        /**
         * A reference to the next item in the list.
         */
        private Album.TrackListItem next;


        /**
         * Creates a list item from a track. Simply wraps a list item around a track so it can be inserted into a linked
         * list of tracks. This list item does NOT maintain its own copy of the original track. It means a track in the
         * list can be modified from the caller who might still maintain a reference, however, the list structure is
         * protected.
         *
         * @param t
         */
        TrackListItem(Track track) {
            this.track = track;
        }
    }
}

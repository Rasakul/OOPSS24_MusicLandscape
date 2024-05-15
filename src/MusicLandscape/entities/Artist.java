package MusicLandscape.entities;

public class Artist {

    private String name = "unknown";

    public Artist(Artist toCopy) {
        this.name = toCopy.name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            this.name = "unknown";
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}

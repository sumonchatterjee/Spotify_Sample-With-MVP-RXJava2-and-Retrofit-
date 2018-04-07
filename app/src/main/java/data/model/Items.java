package data.model;

import java.util.List;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class Items {
    private String added_at;
    private Album album;
    private List<Artist> artists;
    private int disc_number;
    private String duration_ms;
    private String id;
    private String name;
    private int track_number;
    private String uri;

    public void setDisc_number(int disc_number) {
        this.disc_number = disc_number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }

    public String getAdded_at() {
        return added_at;
    }

    public Album getAlbum() {
        return album;
    }

    public String getUri() {
        return uri;
    }

    public String getDuration_ms() {
        return duration_ms;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}

